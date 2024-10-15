package com.pluralsight.financial.tracker;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FinancialTrackerApp {

    // Main method to start the application
    public static void main(String[] args) {
        FinancialTrackerApp app = new FinancialTrackerApp();
        app.run(); // Run the app
    }

    // Constants for file path and date/time formats
    private static final String CSV_FILE_PATH = "./src/main/resources/transactions.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    // List to hold transactions and a scanner for user input
    private ArrayList<Transaction> transactionList;
    private Scanner userInputScanner;

    // Constructor to set up the app
    public FinancialTrackerApp() {
        transactionList = new ArrayList<>();
        userInputScanner = new Scanner(System.in);
        loadTransactions(); // Load existing transactions from the CSV file
    }

    // Method to read transactions from the CSV file
    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Split line into parts
                if (parts.length == 5) { // Check if the line has all required parts
                    LocalDate date = LocalDate.parse(parts[0], DATE_FORMATTER);
                    LocalTime time = LocalTime.parse(parts[1], TIME_FORMATTER);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    // Create a new Transaction and add it to the list
                    transactionList.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }

    // Method to write transactions back to the CSV file
    private void saveTransactions() {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            for (Transaction transaction : transactionList) {
                writer.write(transaction.toCSVString() + "\n"); // Write each transaction
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    // Method to add a deposit
    private void addDeposit() {
        System.out.println("Enter deposit details:");
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(userInputScanner.nextLine(), DATE_FORMATTER);
        System.out.print("Time (HH:MM:SS): ");
        LocalTime time = LocalTime.parse(userInputScanner.nextLine(), TIME_FORMATTER);
        System.out.print("Description: ");
        String description = userInputScanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = userInputScanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(userInputScanner.nextLine());

        // Create a new Transaction for the deposit
        Transaction newDeposit = new Transaction(date, time, description, vendor, amount);
        transactionList.add(newDeposit); // Add it to the list
        saveTransactions(); // Save the updated list to the file
        System.out.println("Deposit added successfully!");
    }

    // Method to display the main menu and handle user choices
    public void run() {
        while (true) {
            System.out.println("\nFinancial Tracker App");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            String choice = userInputScanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit(); // Call method to add a deposit
                    break;
                case "X":
                    System.out.println("Thank you for using the Financial Tracker App. Goodbye!");
                    return; // Exit the application
                default:
                    System.out.println("Invalid option. Please try again."); // Handle invalid input
            }
        }
    }
}