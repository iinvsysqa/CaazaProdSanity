package utils;
import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

	public class SwitchWiFi {

	  public static boolean switchwifi(String ssid, String password) {
	        File profileFile = null;
	        try {
	            // Step 0: Delete the profile if it already exists to ensure a clean slate.
	            System.out.println("Attempting to delete existing profile: " + ssid);
	            runCommand("netsh wlan delete profile name=\"" + ssid + "\"", 10);
	            // We ignore the result of the delete command, as it will fail if the profile doesn't exist, which is fine.

	            // Step 1: Create a temporary Wi-Fi profile XML file.
	            profileFile = createTempProfileFile(ssid, password);
	            System.out.println("Generated temporary profile file at: " + profileFile.getAbsolutePath());

	            // Step 2: Add the new Wi-Fi profile. Check for errors.
	            System.out.println("Adding new WLAN profile for: " + ssid);
	            CommandResult addResult = runCommand("netsh wlan add profile filename=\"" + profileFile.getAbsolutePath() + "\"", 10);
	            if (addResult.exitCode != 0) {
	                System.err.println("Failed to add Wi-Fi profile. Error: " + addResult.error);
	                return false;
	            }
	            Thread.sleep(3000);

	            // Step 3: Connect to the network.
	            System.out.println("Sending command to connect to: " + ssid);
	            CommandResult connectResult = runCommand("netsh wlan connect name=\"" + ssid + "\" ssid=\"" + ssid + "\"", 10);
	             if (connectResult.exitCode != 0) {
	                System.err.println("The connect command failed to execute. Error: " + connectResult.error);
	                // Note: Even with exit code 0, connection is asynchronous.
	            }
	            System.out.println("Connection command sent. Polling for connection status...");

	            // Step 4: Poll for connection success instead of using a fixed sleep.
	            boolean isConnected = pollForConnection(ssid, 30); // Poll for 30 seconds max.
	            if (isConnected) {
	                System.out.println("Successfully connected to Wi-Fi: " + ssid);
	                return true;
	            } else {
	                System.err.println("Failed to connect to " + ssid + " within the timeout period.");
	                return false;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            // Step 5: Clean up the temporary profile file.
	            if (profileFile != null && profileFile.exists()) {
	                if (profileFile.delete()) {
	                    System.out.println("Cleaned up temporary profile file.");
	                } else {
	                    System.err.println("Warning: Failed to delete temporary profile file: " + profileFile.getAbsolutePath());
	                }
	            }
	        }
	    }

	    /**
	     * Polls the system to check if it's connected to the specified SSID.
	     * @param ssid The SSID to check for.
	     * @param timeoutSeconds The maximum time to wait.
	     * @return true if connected, false if timeout is reached.
	     */
	    private static boolean pollForConnection(String ssid, int timeoutSeconds) throws InterruptedException, IOException {
	        long startTime = System.currentTimeMillis();
	        while (System.currentTimeMillis() - startTime < timeoutSeconds * 1000) {
	            CommandResult result = runCommand("netsh wlan show interfaces", 5);
	            // Check both output and error streams for the SSID name.
	            if (result.output.contains("SSID") && result.output.contains(ssid)) {
	                return true;
	            }
	            // Wait 2 seconds before polling again.
	            Thread.sleep(2000);
	        }
	        return false;
	    }


	    /**
	     * Helper class to store the result of a command execution.
	     */
	    private static class CommandResult {
	        final int exitCode;
	        final String output;
	        final String error;

	        CommandResult(int exitCode, String output, String error) {
	            this.exitCode = exitCode;
	            this.output = output;
	            this.error = error;
	        }
	    }

	    /**
	     * Executes a system command and captures its output, error, and exit code.
	     * @param command The command to execute.
	     * @param timeoutSeconds The timeout for the command.
	     * @return a CommandResult object.
	     */
	    private static CommandResult runCommand(String command, int timeoutSeconds) throws IOException, InterruptedException {
	        Process process = Runtime.getRuntime().exec("cmd /c " + command);

	        StringBuilder output = new StringBuilder();
	        StringBuilder error = new StringBuilder();

	        try (BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	             BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
	            
	            String line;
	            while ((line = outputReader.readLine()) != null) {
	                output.append(line).append("\n");
	            }
	            while ((line = errorReader.readLine()) != null) {
	                error.append(line).append("\n");
	            }
	        }

	        if (!process.waitFor(timeoutSeconds, TimeUnit.SECONDS)) {
	            process.destroyForcibly();
	            throw new IOException("Command timed out after " + timeoutSeconds + " seconds: " + command);
	        }

	        if(!output.toString().isEmpty()) System.out.println("OUTPUT:\n" + output);
	        if(!error.toString().isEmpty()) System.err.println("ERROR:\n" + error);
	        
	        return new CommandResult(process.exitValue(), output.toString(), error.toString());
	    }

	    /**
	     * Generates the Wi-Fi profile XML content.
	     * @param ssid The SSID.
	     * @param password The password.
	     * @return The XML content as a String.
	     */
	    private static String generateWifiProfile(String ssid, String password) {
	        // (This function is the same as your original one)
	        return "<?xml version=\"1.0\"?>\n" +
	                "<WLANProfile xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v1\">\n" +
	                "    <name>" + ssid + "</name>\n" +
	                "    <SSIDConfig>\n" +
	                "        <SSID>\n" +
	                "            <name>" + ssid + "</name>\n" +
	                "        </SSID>\n" +
	                "    </SSIDConfig>\n" +
	                "    <connectionType>ESS</connectionType>\n" +
	                "    <connectionMode>auto</connectionMode>\n" +
	                "    <MSM>\n" +
	                "        <security>\n" +
	                "            <authEncryption>\n" +
	                "                <authentication>WPA2PSK</authentication>\n" +
	                "                <encryption>AES</encryption>\n" +
	                "                <useOneX>false</useOneX>\n" +
	                "            </authEncryption>\n" +
	                "            <sharedKey>\n" +
	                "                <keyType>passPhrase</keyType>\n" +
	                "                <protected>false</protected>\n" +
	                "                <keyMaterial>" + password + "</keyMaterial>\n" +
	                "            </sharedKey>\n" +
	                "        </security>\n" +
	                "    </MSM>\n" +
	                "</WLANProfile>";
	    }
	    
	    /**
	     * Creates a temporary file with the Wifi Profile XML content.
	     */
	    private static File createTempProfileFile(String ssid, String password) throws IOException {
	        File tempFile = Files.createTempFile("wifi-profile-", ".xml").toFile();
	        String profileXml = generateWifiProfile(ssid, password);
	        try (FileWriter writer = new FileWriter(tempFile)) {
	            writer.write(profileXml);
	        }
	        return tempFile;
	    }

	    // Example of how to call the new method
	}