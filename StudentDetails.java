import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class StudentDetails {
    private static final String CSV_FILE_NAME = "student_details.csv";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\nStudent CSV Manager");
            System.out.println("1. Enter Student Details");
            System.out.println("2. Remove Student Details");
            System.out.println("3. Search by Name");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    enterStudentDetails();
                    break;
                case 2:
                    removeStudentDetails();
                    break;
                case 3:
                    searchByName();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("Exiting Student CSV Manager.");
    }

    private static void enterStudentDetails() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_NAME));
             PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_NAME, true))) {
    
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
    
            // Check for duplicate entry
            if (isDuplicateId(id, reader)) {
                System.out.println("Student with ID " + id + " already exists. Duplicate entries not allowed.");
                return;
            }
    
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().toUpperCase();
    
            System.out.print("Enter Student Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
    
            System.out.print("Enter Student Gender: ");
            String gender = scanner.nextLine().toUpperCase();
    
            System.out.print("Enter Student Contact: ");
            String contact = scanner.nextLine();
    
            writer.println(id + "," + name + "," + age + "," + gender + "," + contact);
            System.out.println("Student details added successfully.");
        } catch (IOException e) {
            System.err.println("Error reading/writing to CSV file: " + e.getMessage());
        }
    }
    
    private static boolean isDuplicateId(int id, BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int studentId = Integer.parseInt(parts[0]);
            if (studentId == id) {
                return true; // ID already exists
            }
        }
        return false; // ID does not exist
    }
    
    private static void removeStudentDetails() {
    System.out.print("Enter Student ID to remove: ");
    int removeId = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_NAME));
         PrintWriter writer = new PrintWriter(new FileWriter("temp.csv"))) {

        String line;
        boolean found = false;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int studentId = Integer.parseInt(parts[0]);

            if (studentId == removeId) {
                found = true;
            } else {
                writer.println(line);
            }
        }

        if (found) {
            System.out.println("Student details removed successfully.");
        } else {
            System.out.println("Student with ID " + removeId + " not found.");
        }
    } catch (IOException e) {
        System.err.println("Error reading/writing to CSV file: " + e.getMessage());
    }

    // Close the original file before renaming
    try {
        Files.deleteIfExists(Paths.get(CSV_FILE_NAME));
        Files.move(Paths.get("temp.csv"), Paths.get(CSV_FILE_NAME));
        System.out.println("File renamed successfully.");
    } catch (IOException e) {
        System.err.println("Error renaming the file: " + e.getMessage());
    }
}

    
   

    private static void searchByName() {
        System.out.print("Enter Student Name to search: ");
        String searchName = scanner.nextLine().toUpperCase();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
            String line;
            boolean found = false;

            System.out.println("Search results:");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String studentName = parts[1].toUpperCase();

                if (studentName.contains(searchName)) {
                    System.out.println(line);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No matching records found for name: " + searchName);
            }
        } catch (IOException e) {
            System.err.println("Error reading from CSV file: " + e.getMessage());
        }
    }
}
