package utils;
import java.io.*;
import java.util.Properties;

public class ChangeLastTwoDigits {

	

	

	    private static final String CONFIG_FILE = "config.properties";
	    private static final String KEY = "LASTTWODIGITS";

//	    public static void main(String[] args) throws IOException {
//	        updateLastTwoDigitsSequentially();
//	    }

	    public void updateLastTwoDigitsSequentially() throws IOException {
	        Properties props = new Properties();
	        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
	            props.load(in);
	        }

	        String currentValue = props.getProperty(KEY, "KZ").toUpperCase();

	        if (currentValue.length() != 2 || !Character.isLetter(currentValue.charAt(1))) {
	            throw new IllegalArgumentException("Invalid format in lastTwoDigits. Expected 2-letter format like 'KA'");
	        }

	        char prefix = currentValue.charAt(0); // Keep the first character (e.g., 'K')
	        char suffix = currentValue.charAt(1); // Increment the second character (e.g., 'A' → 'B')

	        // Wrap around from 'Z' to 'A'
	        char nextSuffix = (char) ((suffix - 'A' + 1) % 26 + 'A');

	        String nextValue = "" + prefix + nextSuffix;

	        props.setProperty(KEY, nextValue);

	        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
	            props.store(out, null);
	        }

	        System.out.println("Updated " + KEY + " to: " + nextValue);
	    }
	}

