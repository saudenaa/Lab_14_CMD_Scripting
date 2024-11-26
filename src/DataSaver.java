import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>(); // ArrayList to store each record as a CSV entry

        while (true) {
            System.out.print("Enter First Name (or type 'done' to finish): ");
            String firstName = scanner.nextLine();
            if (firstName.equalsIgnoreCase("done")) break; // Stops input if user types "done"

            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter ID Number (6-digit format, e.g., 000001): ");
            String idNumber = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Year of Birth: ");
            String yearOfBirth = scanner.nextLine();

            // Create a CSV record
            String record = String.join(",", firstName, lastName, idNumber, email, yearOfBirth);
            records.add(record); // Add the CSV formatted string to the records list
        }

        // Prompt user to specify file name
        System.out.print("Enter file name to save (without .csv extension): ");
        String fileName = scanner.nextLine() + ".csv"; // Adds ".csv" to the end of the file name

        // Write the records to a CSV file
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("src/" + fileName))) {
            for (String record : records) {
                writer.write(record);
                writer.newLine(); // Write each record on a new line
            }
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }
}

