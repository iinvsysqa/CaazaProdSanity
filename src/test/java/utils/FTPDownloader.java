package utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
public class FTPDownloader {

   	public static void ftpdownloader() {
    	
    	String server = "192.168.10.34";//192.168.10.34
    	int port = 21;
    	String user = "qa_usr";
    	String pass = "nw9f2hgo@123";
        String remotePath = "/Internal_Project/FULL_VALIDATION_PACKAGES_LOGS/PACKAGES/2025/Automation"; 
        // FTP path (end with '/')
      
        String desktopPath = System.getProperty("user.home") + "\\Desktop";
        String localPath = Paths.get(desktopPath, "APK").toString() + "\\";
       // String localPath = "C:\\Users\\QA Automation\\Desktop\\APK"; // Local path (end with '/' or '\')

        FTPClient ftpClient = new FTPClient();
        try {
            // Connect to the FTP server
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode(); // important for passive mode
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            System.out.println("Connected to FTP server: " + server);

            // Change to the remote directory
            boolean success = ftpClient.changeWorkingDirectory(remotePath);
            if (!success) {
                System.out.println("Failed to change directory to: " + remotePath);
                return;
            }

            // List all files in the directory
            FTPFile[] files = ftpClient.listFiles();
            System.out.println("Found " + files.length + " files to download.");

            // Download each file
            for (FTPFile file : files) {
                if (file.isFile()) {
                    String remoteFilePath = remotePath + file.getName();
                    String localFilePath = localPath + file.getName();

                    System.out.println("Downloading: " + remoteFilePath);
                    
                    try (OutputStream outputStream = new FileOutputStream(localFilePath)) {
                        boolean downloaded = ftpClient.retrieveFile(file.getName(), outputStream);
                        if (downloaded) {
                            System.out.println("Successfully downloaded: " + file.getName());
                        } else {
                            System.out.println("Failed to download: " + file.getName());
                        }
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                    System.out.println("Disconnected from FTP server");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}