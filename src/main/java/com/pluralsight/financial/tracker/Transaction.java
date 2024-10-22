
package com.pluralsight.financial.tracker;


import java.time.LocalDate; // (yyyy-MM-dd)

import java.time.LocalTime; // (HH:mm)


// This class represents a financial transaction with various attributes (fields)
public class Transaction {
    // Instance variables (fields)
    // Private, so the variables can only be accessed within this class
    private LocalDate transactionDate;
    // LocalTime object to show the time that the transaction occurred
    private LocalTime transactionTime;
    // String is used to show the description of the transaction
    // String is a class that represents a sequence of characters
    private String transactionDescription;
    // String to store the name of the vendor/merchant
    private String transactionVendor;
    // double is the primitive data type used to store decimal numbers
    // Stores the monetary amount of the transaction
    private double transactionAmount;


    // Parameters are placed inside parentheses and used to initialize the object
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        // 'this' keyword refers to the current instance of the class
        // Assigns the parameter 'date' to the instance variable 'transactionDate'
        this.transactionDate = date;
        // Assigns the parameter 'time' to the instance variable 'transactionTime'
        this.transactionTime = time;
        // Assigns the parameter 'description' to the instance variable 'transactionDescription'
        this.transactionDescription = description;
        // Assigns the parameter 'vendor' to the instance variable 'transactionVendor'
        this.transactionVendor = vendor;
        // Assigns the parameter 'amount' to the instance variable 'transactionAmount'
        this.transactionAmount = amount;
    }

    // Getter methods - provide read access to private instance variables

    // Returns the transaction date
    public LocalDate getTransactionDate() {
        // Return statement sends the value back to the caller
        return transactionDate;
    }

    // Returns the transaction time
    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    // Returns the transaction description
    public String getTransactionDescription() {
        return transactionDescription;
    }

    // Returns the vendor name
    public String getTransactionVendor() {
        return transactionVendor;
    }

    // Returns the transaction amount
    public double getTransactionAmount() {
        return transactionAmount;
    }

    // Setter methods - provide write access to private instance variables
    // void means these methods don't return any value
    // Sets a new date for the transaction
    public void setTransactionDate(LocalDate date) {
        // Updates the transactionDate with the new value
        this.transactionDate = date;
    }

    // Sets a new time for the transaction
    public void setTransactionTime(LocalTime time) {
        this.transactionTime = time;
    }

    // Sets a new description for the transaction
    public void setTransactionDescription(String description) {
        this.transactionDescription = description;
    }

    // Sets a new vendor for the transaction
    public void setTransactionVendor(String vendor) {
        this.transactionVendor = vendor;
    }

    // Sets a new amount for the transaction
    public void setTransactionAmount(double amount) {
        this.transactionAmount = amount;
    }

    // Utility method to convert the transaction into a CSV format
    // Returns a String representation of the transaction with fields separated by pipe characters
    public String toCSVString() {

        return String.format("%s|%s|%s|%s|%.2f",
                transactionDate, transactionTime, transactionDescription, transactionVendor, transactionAmount);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%.2f \n",
                transactionDate, transactionTime, transactionDescription, transactionVendor, transactionAmount);
    }
}