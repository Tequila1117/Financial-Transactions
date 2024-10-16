package com.pluralsight.financial.tracker;

import java.util.ArrayList;
import java.util.List;

public class Ledger {

    // helper class for the ledger menu

    //all methods related to the Ledger
    //static methods make the most sense

    public static void viewAllTransactions(List<Transaction> transactionList){

            System.out.println("Ledger of Transactions:");
            // Check if we have any transactions
            if (transactionList.isEmpty()) {
                System.out.println("No transactions found.");
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

        // view Deposits

    public static List<Transaction> viewPayments(List<Transaction> transactionList){
        List<Transaction> payments = new ArrayList<>();
        if(!transactionList.isEmpty()){
            for(Transaction tran: transactionList){
                if(String.valueOf(tran.getTransactionAmount()).contains("-")){
                    payments.add(tran);
                }
            }
        }
        return payments;
    }

        //view payments






    }

