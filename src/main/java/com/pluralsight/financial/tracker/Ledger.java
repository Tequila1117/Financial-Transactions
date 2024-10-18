package com.pluralsight.financial.tracker;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {


    // Method to show all transactions
    public static void viewLedger(ArrayList<Transaction> transactionList) {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println("\n Ledger Menu");
        System.out.println(""" 
                     A) View All Transactions
                     D) View Deposit
                     P) View Payment
                     R) Reports
                     H) Home
                     Choose an option:
                    
                     
                """);


        boolean ledgerMenuRunning = true;

        // create while loop that keeps running through prompts until user exits
        while (ledgerMenuRunning) {
            String choice = userInputScanner.nextLine().toUpperCase();

            // Switch statement to handle different menu choices
            switch (choice) {
                case "D":
                    for (Transaction tran : viewDeposits(transactionList)) {
                        System.out.print(tran);
                    }
                    break;
                case "P":
                    for (Transaction tran : viewPayments(transactionList))
                    // Call payment method
                    System.out.print(tran);
                    break;

                case "A":
                    System.out.println(" View All Transactions");

                    break;
                case "R":
                    Reports r = new Reports();
                    Reports.reports();


                    break;
                case "H":
                    System.out.println("Return to Home Page");
                    ledgerMenuRunning = false;
                   break;  // Going back to /home Page
                default:
                    System.out.println("Sorry, That was an invalid option. Please try again.");  // Handle invalid input

            }

        }


    }




    public static void viewAllTransactions(List<Transaction> transactionList) {

        System.out.println("Ledger of All Transactions:");
        // Check if we have any transactions
        if (transactionList.isEmpty()) {
            System.out.println("No Transactions Found.");
        } else {
            // For each loop to go through and display all transactions
            for (Transaction transaction : transactionList) {
                // Print each transaction with formatted output
                System.out.printf("%s %s  | %s  | %s  | %.2f%n",
                        transaction.getTransactionDate(),
                        transaction.getTransactionTime(),
                        transaction.getTransactionDescription(),
                        transaction.getTransactionVendor(),
                        transaction.getTransactionAmount());
            }
        }
    }


    //view deposits
    public static ArrayList<Transaction> viewDeposits(ArrayList<Transaction> transactionList) {
        ArrayList<Transaction> deposits = new ArrayList<>();
        if (transactionList.isEmpty()) {
            System.out.println("No Transactions Found.");
        }
        for (Transaction tran : transactionList) {
            if (tran.getTransactionAmount() > 0) {  // Check for positive amount
                deposits.add(tran);
            }
        }
        return deposits;
    }

    // view Payments (debits)

    public static ArrayList<Transaction> viewPayments(ArrayList<Transaction> transactionList) {
        ArrayList<Transaction> payments = new ArrayList<>();
        if (transactionList.isEmpty())
            System.out.println("No Transactions Found.");
        {
            for (Transaction tran : transactionList) {
                if (tran.getTransactionAmount() < 0) {
                    payments.add(tran);
                    System.out.print(tran);
                }
            }
        }
        return payments;
    }
}