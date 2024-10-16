package com.pluralsight.financial.tracker;


import java.io.*;  // For handling input/output operations (reading/writing files)
import java.time.LocalDate;  // For working with dates
import java.time.LocalDateTime;
import java.time.LocalTime;  // For working with times
import java.time.format.DateTimeFormatter;  // For formatting dates and times
import java.util.ArrayList;  // For creating a list that can grow or shrink
import java.util.Scanner;  // For reading user input from the keyboard

// Main class definition - this is the blueprint for our application
public class FinancialTrackerApp {
    // Constants - values that don't change throughout the program
    private static final String CSV_FILE_PATH = "src/main/resources/transactions.csv";  // Location of our data file
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Format for dates
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");  // Format for times

    // Instance variables - data that belongs to each instance of our application
    private ArrayList<Transaction> transactionList;  // List to store all transactions
    private Scanner userInputScanner;  // Tool for reading user input
    // Main method - this is where the program starts running
    public static void main(String[] args) {
        FinancialTrackerApp app = new FinancialTrackerApp();  // Create a new instance of our application
        app.run();  // Start running the application
    }



    // Constructor - special method that sets up our application when it's created
    public FinancialTrackerApp() {
        transactionList = new ArrayList<>();  // Create an empty list for transactions
        userInputScanner = new Scanner(System.in);  // Create a scanner to read user input
        loadTransactions();  // Load any existing transactions from our file
    }

    // Method to load transactions from our CSV file
    private void loadTransactions() {
        // Try-with-resources block - automatically closes the file when we're done
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;  // Variable to store each line we read
            // While loop - keeps reading lines until we reach the end of the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");  // Split each line into parts at the | character
                // Check if we have all 5 required pieces of information
                if (parts.length == 5) {
                    // Convert the text data into the proper types (date, time, text, number)
                    LocalDate date = LocalDate.parse(parts[0], DATE_FORMATTER);
                    LocalTime time = LocalTime.parse(parts[1], TIME_FORMATTER);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    // Create a new transaction and add it to our list
                    transactionList.add(new Transaction(date, time, description, vendor, amount));
                }
            }
            // Catch block - handles any errors that might occur while reading the file
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }

    // Method to save all transactions back to the CSV file
    private void saveTransactions() {
        // Try-with-resources block to write to the file
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            // For each loop - goes through every transaction in our list
            for (Transaction transaction : transactionList) {
                writer.write(transaction.toCSVString() + "\n");  // Write the transaction and add a new line
            }
            // Catch block for handling any writing errors
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    // Method to handle adding a new deposit
    private void addDeposit() {
        System.out.println("Enter deposit details:");  // Show instructions to user
        // Get all the necessary information from the user
        System.out.print("Date (YYYY-MM-DD): ");

    //    LocalDateTime todaysDate = LocalDateTime.now();
        LocalDate date = LocalDate.parse(userInputScanner.nextLine(), DATE_FORMATTER);
     //   LocalDate date2 = todaysDate.toLocalDate();
      //  LocalDate formatedDate = LocalDate.parse(date2.toString(),DATE_FORMATTER);
       System.out.print("Time (HH:MM:SS): ");
        LocalTime time = LocalTime.parse(userInputScanner.nextLine(), TIME_FORMATTER);
      //  LocalTime time2 = todaysDate.toLocalTime();
       // LocalTime formattedTime = LocalTime.parse(time2.toString(), TIME_FORMATTER);

        System.out.print("Description: ");
        String description = userInputScanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = userInputScanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(userInputScanner.nextLine());

        // Create new transaction object with the collected information
        Transaction newDeposit = new Transaction(date, time, description, vendor, amount);
        transactionList.add(0, newDeposit);  // Add to the beginning of the list
        saveTransactions();  // Save changes to the file
        System.out.println("Deposit added successfully!");  // Confirm to the user
    }

    // Method to handle making a payment (similar to addDeposit but amount is negative)
    private void makePayment() {
        System.out.println("Enter payment details:");
        // Get all the necessary information from the user
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

        // Create new transaction object with negative amount (since it's a payment)
        Transaction newPayment = new Transaction(date, time, description, vendor, -amount);
        transactionList.add(newPayment);  // Add to the end of the list
        saveTransactions();  // Save changes to the file
        System.out.println("Payment added successfully!");  // Confirm to the user
    }

    // Method to show all transactions
    private void viewLedger() {
//        System.out.println("Ledger of Transactions:");
//        // Check if we have any transactions
//        if (transactionList.isEmpty()) {
//            System.out.println("No transactions found.");
//        } else {
//            // For each loop to go through and display all transactions
//            for (Transaction transaction : transactionList) {
//                // Print each transaction with formatted output
//                System.out.printf("%s %s  | %s  | %s  | %.2f%n",
//                        transaction.getTransactionDate(),
//                        transaction.getTransactionTime(),
//                        transaction.getTransactionDescription(),
//                        transaction.getTransactionVendor(),
//                        transaction.getTransactionAmount());
//            }
      //  }
        System.out.println("\n Ledger Menu");
        System.out.println("A) View All Transactions");
        System.out.println("D) View Deposit");
        System.out.println("P) View Payment");
        System.out.println("L) ");
        System.out.println("X) Exit");
        System.out.print("Choose an option: ");

        boolean ledgerMenuRunning = true;

        while(ledgerMenuRunning) {
            String choice = userInputScanner.nextLine().toUpperCase();

            // Switch statement to handle different menu choices
            switch (choice) {
                case "D":
                    // Call Ledger.viewDeposists  method
                    break;
                case "P":
                    Ledger.viewPayments(transactionList); // Call payment method
                    break;

                case "A":
                    Ledger.viewAllTransactions(transactionList);
                        break;
                case "":
                    // Call ledger method
                    break;
                case "H":
                    System.out.println("Thank you for using the Financial Tracker App. Goodbye!");
                    ledgerMenuRunning = false;
                    break;  // Going basck to /home Page
                default:
                    System.out.println("Sorry, That was an invalid option. Please try again.");  // Handle invalid input

            }

        }


    }

    // Method that runs the main menu loop
    public void run() {
        // While loop that keeps running until user chooses to exit
        while (true) {
            // Display menu options
            System.out.println("\nFinancial Tracker App");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            // Get user's choice and convert to uppercase
            String choice = userInputScanner.nextLine().toUpperCase();

            // Switch statement to handle different menu choices
            switch (choice) {
                case "D":
                    addDeposit();  // Call deposit method
                    break;
                case "P":
                    makePayment();  // Call payment method
                    break;
                case "L":
                    viewLedger(); // Call ledger method
                    break;
                case "X":
                    System.out.println("Thank you for using the Financial Tracker App. Goodbye!");
                    return;  // Exit the program
                default:
                    System.out.println("Sorry, That was an invalid option. Please try again.");  // Handle invalid input


            }
        }
    }
}