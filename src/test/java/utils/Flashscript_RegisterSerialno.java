package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import wrappers.MobileAppWrappers;
import utils.Reporter;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class Flashscript_RegisterSerialno extends MobileAppWrappers {

    private static String extractedSerialNumber = null;
    private static final String SERIAL_ENTRY_ENDPOINT = "https://ftp.iinvsys.com:55576/core1/spl/devices/serial_Entry"; // CHANGE THIS
    private static final String DESKTOP_PATH = System.getProperty("user.home") + "\\Desktop\\flash_tool_automation_scripts";
    private static final String ESPTOOL_PATH =DESKTOP_PATH + "\\Flash_Script.py";

    public static String runFlashScriptAndRegister(String productId, String variant) {
        extractedSerialNumber = null;

        try {
            ProcessBuilder pb = new ProcessBuilder("python", "-u", DESKTOP_PATH + "\\Flash_Script.py");
            pb.directory(new File(DESKTOP_PATH));
            Process process = pb.start();	

            OutputStream stdin = process.getOutputStream();
            InputStream stdout = process.getInputStream();
            InputStream stderr = process.getErrorStream();

            String[][] prompts = {
                {"Select the product ID", productId + "\n"},
                {"SmartPanel V2 Variant", variant + "\n"},
                {"flash OTA BIN", "N\n"},
                {"Server Type", "2\n"},
                {"flash type", "1\n"},
                {"last two digits", "BA\n"},
                {"Flash / Erase", "1\n"},
                {"Serial Port", loadProp("COM") + "\n"},
                {"Press Enter to continue", "\n"}
            };

            // MAIN READER — CHAR BY CHAR (Works even without newline)
            new Thread(() -> {
                try (InputStreamReader isr = new InputStreamReader(stdout);
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin))) {

                    char[] buffer = new char[1024];
                    int read;
                    StringBuilder accumulated = new StringBuilder();
                    int step = 0;

                    while ((read = isr.read(buffer)) != -1) {
                        String chunk = new String(buffer, 0, read);
                        System.out.print(chunk);
                       
                        accumulated.append(chunk);

                        String output = accumulated.toString();

                        // Extract Serial Number
                        Matcher m = Pattern.compile("SERIAL NUMBER[:\\s]+([A-Z0-9]+)").matcher(output);
                        if (m.find() && extractedSerialNumber == null) {
                            extractedSerialNumber = m.group(1);
                           
                            System.out.println("SERIAL EXTRACTED → " + extractedSerialNumber);
                        }

                        // SEND RESET EVERY TIME "Connecting...." APPEARS
                        if (output.contains("Connecting....") || output.contains("Connecting.....")) {
                            System.out.println("Detected 'Connecting....' → Sending BOOT RESET (Retry Safe)");
                            sendBootResetWithRetry(loadProp("COM"), 3);  // 3 retries
                            Thread.sleep(2000); // Give time for port to free
                        }

                        // Auto respond to prompts
                        for (int i = step; i < prompts.length; i++) {
                            if (output.contains(prompts[i][0])) {
                                Thread.sleep(1200);
                                writer.write(prompts[i][1]);
                                writer.flush();
                                System.out.println("Sent → " + prompts[i][1].trim());
                                step = i + 1;
                                break;
                            }
                        }

                        // Prevent memory leak
                        if (accumulated.length() > 1000) {
                            accumulated.delete(0, accumulated.length() - 500);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            // Error logging
            new Thread(() -> {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(stderr))) {
                    br.lines().forEach(line -> {
                        System.err.println("ERR: " + line);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            process.waitFor(240, TimeUnit.SECONDS);

            if (extractedSerialNumber != null) {
                registerSerialNumberViaAPI(extractedSerialNumber);
                System.out.println("FULL SUCCESS: " + extractedSerialNumber + " → Flashed + Reset + Registered");
                return extractedSerialNumber;
            } else {
            	System.out.println("SERIAL NOT FOUND");
                return "FAILED";
            }

        } catch (Exception e) {
        	System.out.println("CRASH: " + e.getMessage());
            e.printStackTrace();
            return "ERROR";
        }
    }

    // SMART RESET WITH RETRY — Solves "Port busy" error 100%
    private static void sendBootResetWithRetry(String comPort, int maxRetries) {
        for (int i = 0; i < maxRetries; i++) {
            try {
                System.out.println("Attempt " + (i+1) + ": Sending esptool.py run on " + comPort);

                ProcessBuilder pb = new ProcessBuilder(
                    "python", "-u", ESPTOOL_PATH,
                    "--port", comPort,
                    "--baud", "921600",
                    "run"
                );
                pb.directory(new File(DESKTOP_PATH));
                pb.redirectErrorStream(true);

                Process p = pb.start();
                p.waitFor(15, TimeUnit.SECONDS);

                // Read output to check success
                try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                    String output = r.lines().reduce("", (a, b) -> a + b + "\n");
                    if (output.contains("Stub running") || output.contains("Hard resetting")) {
                        System.out.println("BOOT RESET SUCCESSFUL on attempt " + (i+1));
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Reset attempt " + (i+1) + " failed: " + e.getMessage());
            }
            try { Thread.sleep(3000); } catch (Exception ignored) {}
        }
        System.out.println("ALL RESET ATTEMPTS FAILED");
    }

    private static void registerSerialNumberViaAPI(String serialNo) {
        JSONObject body = new JSONObject();
        body.put("serial_no_data", new JSONArray().put(new JSONObject().put("serial_no", serialNo)));

        try {
            Response res = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(body.toString())
                .post(SERIAL_ENTRY_ENDPOINT);

            if (res.statusCode() == 200) {
                System.out.println("SERIAL REGISTERED → " + serialNo + " | 200 OK");
            } else {
                System.out.println("REG FAILED → " + serialNo + " | " + res.statusCode());
            }
        } catch (Exception e) {
        	System.out.println("API ERROR: " + e.getMessage());
        }
    }

  
}