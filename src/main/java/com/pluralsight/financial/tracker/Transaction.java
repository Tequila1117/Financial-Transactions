package com.pluralsight.financial.tracker;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    // Private variables to store transaction details
    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private String transactionDescription;
    private String transactionVendor;
    private double transactionAmount;

    // Constructor to create a Transaction object
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.transactionDate = date;
        this.transactionTime = time;
        this.transactionDescription = description;
        this.transactionVendor = vendor;
        this.transactionAmount = amount;
    }

    // Methods to get transaction details
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public String getTransactionVendor() {
        return transactionVendor;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    // Methods to change transaction details
    public void setTransactionDate(LocalDate date) {
        this.transactionDate = date;
    }

    public void setTransactionTime(LocalTime time) {
        this.transactionTime = time;
    }

    public void setTransactionDescription(String description) {
        this.transactionDescription = description;
    }

    public void setTransactionVendor(String vendor) {
        this.transactionVendor = vendor;
    }

    public void setTransactionAmount(double amount) {
        this.transactionAmount = amount;
    }

    // Method to convert the Transaction object to a CSV string
    public String toCSVString() {
        return String.format("%s|%s|%s|%s|%.2f",
                transactionDate, transactionTime, transactionDescription, transactionVendor, transactionAmount);
    }
}