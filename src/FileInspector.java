import javax.swing.*;
import java.io.*;
import java.nio.file.*;

public class FileInspector {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser("src"); // Opens JFileChooser in the src directory
        int option = fileChooser.showOpenDialog(null); // Shows the file dialog

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); // Gets the selected file

            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    wordCount += line.split("\\s+").length; // Splits line into words and counts them
                    charCount += line.length(); // Adds the character count of each line
                }

                // Display the summary report
                System.out.println("File Summary:");
                System.out.println("File Name: " + file.getName());
                System.out.println("Number of Lines: " + lineCount);
                System.out.println("Number of Words: " + wordCount);
                System.out.println("Number of Characters: " + charCount);
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}

