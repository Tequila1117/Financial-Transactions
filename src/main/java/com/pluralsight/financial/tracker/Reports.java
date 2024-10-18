package com.pluralsight.financial.tracker;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Reports {

    // Method to display the reports menu and handle user input
    public static void reports() {
        Scanner userInputScanner = new Scanner(System.in); // Scanner to read user input
        boolean userResponse = true; // Flag to control the menu loop
        while (userResponse) {
            System.out.println("\n Reports Menu");
            System.out.println(""" 
                         1) Month To Date
                         2) Previous Month
                         3) Year To Date
                         4) Previous Year
                         5) Search By Vendor
                         6) Return to Ledger
                         Choose an option:
                    """);

            String option = userInputScanner.nextLine(); // Get user input

            // Switch statement to handle different report options
            switch (option) {
                case "1":
                    System.out.println("Showing Month to Date");
                    // Call the method for month-to-date transactions
                    break;
                case "2":
                    System.out.println("Showing Previous Month");
                    // Call the method for previous month's transactions
                    break;
                case "3":
                    System.out.println("Showing Year To Date");
                    // Call the method for year-to-date transactions
                    break;
                case "4":
                    System.out.println("Showing Previous Year");
                    // Call the method for previous year's transactions
                    break;
                case "5":
                    System.out.println("Vendor Search");
                    // Call the method to search transactions by vendor
                    break;
                case "6":
                    System.out.println("Return To Ledger");
                    userResponse = false; // Set flag to false to exit the loop
                    break;
                default:
                    System.out.println("Sorry, That was an invalid option. Please try again."); // Handle invalid input
            }
        }
    }

    // Method for Month to Date transactions
    public static void monthToDate(ArrayList<Transaction> transactionList) {
        LocalDate today = LocalDate.now(); // Get today's date
        LocalDate firstDayOfMonth = today.withDayOfMonth(1); // Get the first day of the current month
        System.out.println("Month to Date Transactions:");

        // Loop through the transaction list to find transactions in the current month
        for (Transaction transaction : transactionList) {
            // Check if the transaction date is within the current month
            if (transaction.getTransactionDate().isAfter(firstDayOfMonth.minusDays(1)) &&
                    transaction.getTransactionDate().isBefore(today.plusDays(1))) {
                System.out.println(transaction); // Print the transaction
            }
        }
    }

    // Method for Previous Month transactions
    public static void previousMonth(ArrayList<Transaction> transactionList) {
        LocalDate today = LocalDate.now(); // Get today's date
        LocalDate firstDayOfLastMonth = today.minusMonths(1).withDayOfMonth(1); // Get the first day of the previous month
        LocalDate lastDayOfLastMonth = today.minusMonths(1).withDayOfMonth(today.minusMonths(1).lengthOfMonth()); // Get the last day of the previous month
        System.out.println("Previous Month Transactions:");

        // Loop through the transaction list to find transactions from the previous month
        for (Transaction transaction : transactionList) {
            // Check if the transaction date is within the previous month
            if (transaction.getTransactionDate().isAfter(firstDayOfLastMonth.minusDays(1)) &&
                    transaction.getTransactionDate().isBefore(lastDayOfLastMonth.plusDays(1))) {
                System.out.println(transaction); // Print the transaction
            }
        }
    }

    // Method for Year To Date transactions
    public static void yearToDate(ArrayList<Transaction> transactionList) {
        LocalDate today = LocalDate.now(); // Get today's date
        LocalDate firstDayOfYear = today.withDayOfYear(1); // Get the first day of the current year
        System.out.println("Year To Date Transactions:");

        // Loop through the transaction list to find transactions in the current year
        for (Transaction transaction : transactionList) {
            // Check if the transaction date is within the current year
            if (transaction.getTransactionDate().isAfter(firstDayOfYear.minusDays(1)) &&
                    transaction.getTransactionDate().isBefore(today.plusDays(1))) {
                System.out.println(transaction); // Print the transaction
            }
        }
    }

    // Method for Previous Year transactions
    public static void previousYear(ArrayList<Transaction> transactionList) {
        LocalDate today = LocalDate.now(); // Get today's date
        LocalDate firstDayOfLastYear = today.minusYears(1).withDayOfYear(1); // Get the first day of the previous year
        LocalDate lastDayOfLastYear = today.minusYears(1).withDayOfYear(today.minusYears(1).lengthOfYear()); // Get the last day of the previous year
        System.out.println("Previous Year Transactions:");

        // Loop through the transaction list to find transactions from the previous year
        for (Transaction transaction : transactionList) {
            // Check if the transaction date is within the previous year
            if (transaction.getTransactionDate().isAfter(firstDayOfLastYear.minusDays(1)) &&
                    transaction.getTransactionDate().isBefore(lastDayOfLastYear.plusDays(1))) {
                System.out.println(transaction); // Print the transaction
            }
        }
    }

    // Method to search transactions by vendor
    public static void searchByVendor(ArrayList<Transaction> transactionList) {
        Scanner userInputScanner = new Scanner(System.in); // Scanner to read user input
        System.out.print("Enter vendor name to search: "); // Prompt for vendor name
        String vendorName = userInputScanner.nextLine(); // Read vendor name
        System.out.println("Transactions for Vendor: " + vendorName);

        boolean found = false; // Flag to check if any transactions are found
        // Loop through the transaction list to find matches
        for (Transaction transaction : transactionList) {
            // Check if the transaction vendor matches the search term (not case sensitive)
            if (transaction.getTransactionVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(transaction); // Print the matching transaction
                found = true; // Update the found flag
            }
        }

        // If no transactions were found, print a message
        if (!found) {
            System.out.println("No transactions found for vendor: " + vendorName);
        }
    }
}
