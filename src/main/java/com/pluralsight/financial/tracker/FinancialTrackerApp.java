package com.pluralsight.financial.tracker;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// Main class for the financial tracker application
public class FinancialTrackerApp {
    // Constant that holds the file path for CSV data
     public static final String CSV_FILE_PATH = "src/main/resources/transactions.csv";
    // Formatter for date input
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // Formatter for time input
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    // List to store all transactions
    private ArrayList<Transaction> transactionList;
    // Scanner for reading user input
    private Scanner userInputScanner;

    // Main method: entry point of the program
    public static void main(String[] args) {
        FinancialTrackerApp app = new FinancialTrackerApp();  // Create a new instance of the app
        app.run();  // Start the app
    }

    // Constructor: initializes the app
    public FinancialTrackerApp() {
        transactionList = new ArrayList<>();  // Create an empty list for transactions
        userInputScanner = new Scanner(System.in);  // Initialize scanner for user input
        loadTransactions();  // Load existing transactions from the CSV file
    }

    // Method to load transactions from the CSV file
    private void loadTransactions() {
        // Try-with-resources to ensure the file is closed after reading
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;  // Variable to hold each line of the file
            // Loop to read each line until the end of the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");  // Split line into parts using '|'
                // Check if the line has the correct number of parts
                if (parts.length == 5) {
                    // Parse the parts into appropriate types
                    LocalDate date = LocalDate.parse(parts[0], DATE_FORMATTER);
                    LocalTime time = LocalTime.parse(parts[1], TIME_FORMATTER);
                    String description = parts[2];  // Text description
                    String vendor = parts[3];  // Vendor name
                    double amount = Double.parseDouble(parts[4]);  // Transaction amount
                    // Create a new transaction object and add it to the list
                    transactionList.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (IOException e) {
            // Print error message if loading transactions fails
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }

    // Method to save transactions back to the CSV file
    private void saveTransactions() {
        // Try-with-resources to write to the file
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            // Loop through each transaction in the list
            for (Transaction transaction : transactionList) {
                writer.write(transaction.toCSVString() + "\n");  // Write transaction to file
            }
        } catch (IOException e) {
            // Print error message if saving transactions fails
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    // Method to add a new deposit
    private void addDeposit() {
        System.out.println("Enter deposit details:");  // Prompt for deposit details
        // Get date from user
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(userInputScanner.nextLine(), DATE_FORMATTER);

        // Get time from user
        System.out.print("Time (HH:MM): ");
        LocalTime time = LocalTime.parse(userInputScanner.nextLine(), TIME_FORMATTER);

        // Get description from user
        System.out.print("Description: ");
        String description = userInputScanner.nextLine();
        // Get vendor from user
        System.out.print("Vendor: ");
        String vendor = userInputScanner.nextLine();
        // Get amount from user
        System.out.print("Amount: ");
        double amount = Double.parseDouble(userInputScanner.nextLine());

        // Create a new transaction object
        Transaction newDeposit = new Transaction(date, time, description, vendor, amount);
        transactionList.add(0, newDeposit);  // Add it to the start of the list
        saveTransactions();  // Save changes to the file
        System.out.println("Great!! Deposit added successfully!");  // Confirm success to the user
    }

    // Method to make a payment (debit)
    private void makePayment() {
        System.out.println("Enter payment details:");  // Prompt for payment details
        // Get date from user
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(userInputScanner.nextLine(), DATE_FORMATTER);
        // Get time from user
        System.out.print("Time (HH:MM): ");
        LocalTime time = LocalTime.parse(userInputScanner.nextLine(), TIME_FORMATTER);
        // Get description from user
        System.out.print("Description: ");
        String description = userInputScanner.nextLine();
        // Get vendor from user
        System.out.print("Vendor: ");
        String vendor = userInputScanner.nextLine();
        // Get amount from user
        System.out.print("Amount: ");
        double amount = Double.parseDouble(userInputScanner.nextLine());

        // Create a new transaction object with a negative amount for debit
        Transaction newPayment = new Transaction(date, time, description, vendor, -amount);
        transactionList.add(newPayment);  // Add it to the end of the list
        saveTransactions();  // Save changes to the file
        System.out.println("Payment added successfully!");  // Confirm success to the user
    }

    // Method that runs the main menu loop
    public void run() {
        // while-Loop that runs until the user chooses to exit
        while (true) {
            // Display menu options to the user
            System.out.println("\nFinancial Tracker App");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            // Read user input and convert it to uppercase
            String choice = userInputScanner.nextLine().toUpperCase();

            // Switch statement to handle different user choices
            switch (choice) {
                case "D":
                    addDeposit();  // Call method to add deposit
                    break;
                case "P":
                    makePayment();  // Call method to make payment
                    break;
                case "L":
                    Ledger.viewLedger(transactionList);  // Call method to view ledger

                case "X":
                    System.out.println("Thank you for using the Financial Tracker App. Goodbye!");  // Exit message
                    return;  // Exit the program
                default:
                    System.out.println("Sorry, That was an invalid option. Please try again.");  // Handle invalid input
            }
        }
    }
}