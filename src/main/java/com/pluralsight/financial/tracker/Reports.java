package com.pluralsight.financial.tracker;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Reports {

    public static void reports() {
        Scanner userInputScanner = new Scanner(System.in);
        boolean userResponse = true;
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

            String option = userInputScanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Showing Month to Date");
                    // put Method for month to date
                    break;
                case "2":
                    System.out.println("Showing Previous Month");

                    break;
                case "3":
                    System.out.println("Showing Year To Date");

                    break;
                case "4":
                    System.out.println("Showing Previous Year");

                    break;
                case "5":
                    System.out.println("Vendor Search");
                    
                case "6":
                    System.out.println("Return To Ledger");
                    boolean response = false;
                    break;
                default:
                    System.out.println("Sorry, That was an invalid option. Please try again.");
            }
        }
    }

    // Method for Month to Date
    public static void monthToDate(ArrayList<Transaction> transactionList) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        System.out.println("Month to Date Transactions:");

        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionDate().isAfter(firstDayOfMonth.minusDays(1)) &&
                    transaction.getTransactionDate().isBefore(today.plusDays(1))) {
                System.out.println(transaction);
            }
        }
    }

    // Method for Previous Month
    public static void previousMonth(ArrayList<Transaction> transactionList) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = today.minusMonths(1).withDayOfMonth(today.minusMonths(1).lengthOfMonth());
        System.out.println("Previous Month Transactions:");

        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionDate().isAfter(firstDayOfLastMonth.minusDays(1)) &&
                    transaction.getTransactionDate().isBefore(lastDayOfLastMonth.plusDays(1))) {
                System.out.println(transaction);
            }
        }
    }

    // Method for Year To Date
    public static void yearToDate(ArrayList<Transaction> transactionList) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfYear = today.withDayOfYear(1);
        System.out.println("Year To Date Transactions:");

        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionDate().isAfter(firstDayOfYear.minusDays(1)) &&
                    transaction.getTransactionDate().isBefore(today.plusDays(1))) {
                System.out.println(transaction);
            }
        }
    }

    // Method for Previous Year
    public static void previousYear(ArrayList<Transaction> transactionList) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfLastYear = today.minusYears(1).withDayOfYear(1);
        LocalDate lastDayOfLastYear = today.minusYears(1).withDayOfYear(today.minusYears(1).lengthOfYear());
        System.out.println("Previous Year Transactions:");

        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionDate().isAfter(firstDayOfLastYear.minusDays(1)) &&
                    transaction.getTransactionDate().isBefore(lastDayOfLastYear.plusDays(1))) {
                System.out.println(transaction);
            }
        }
    }

    // Method to search by vendor
    public static void searchByVendor(ArrayList<Transaction> transactionList) {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.print("Enter vendor name to search: ");
        String vendorName = userInputScanner.nextLine();
        System.out.println("Transactions for Vendor: " + vendorName);

        boolean found = false;
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(transaction);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for vendor: " + vendorName);
        }
    }
}