package com.pluralsight.financial.tracker;

import java.time.LocalDate;
import java.util.Scanner;

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
                    // put Method for previous month
                    break;
                case "3":
                    System.out.println("Showing Year To Date");
                    //put method for year to date
                    break;
                case "4":
                    System.out.println("Showing Previous Year");
                    //put method for prev year
                    break;
                case "5":
                    System.out.println("Vendor Search");
                    // put method to search vendor
                case "6":
                    System.out.println("Return To Ledger");
                    boolean response = false;
                    break;
                default:
                    System.out.println("Sorry, That was an invalid option. Please try again.");
            }
        }
    }
    //Method for Month to Date
    public static void monthToDate(){
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        System.out.println("Month to Date");

        for(Ledger ledger; Transaction) {
            if (!ledger.g)
        }
    }
}
