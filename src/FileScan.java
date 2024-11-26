import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileScan {
    public static void main(String[] args) {
        File file = null;

        // Check if a command-line argument is passed
        if (args.length > 0) {
            file = new File(args[0]); // Get the file name from the command-line argument
            if (!file.exists() || !file.isFile()) {
                System.out.println("Error: The specified file does not exist or is not valid.");
                return;
            }
        } else {
            // Use JFileChooser to select the file if no argument is passed
            JFileChooser fileChooser = new JFileChooser("src");
            int option = fileChooser.showOpenDialog(null);

            if (option == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                System.out.println("No file selected.");
                return;
            }
        }

        // Process the file and display the summary
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            int lineCount = 0, wordCount = 0, charCount = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                wordCount += line.split("\\s+").length;
                charCount += line.length();
            }

            // Display the file summary
            System.out.println("File Summary:");
            System.out.println("File Name: " + file.getName());
            System.out.println("Number of Lines: " + lineCount);
            System.out.println("Number of Words: " + wordCount);
            System.out.println("Number of Characters: " + charCount);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
