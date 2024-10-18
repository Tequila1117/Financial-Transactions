                                            ***Financial Tracker Application***

Overview
The Financial Tracker Application is a simple command-line interface tool for tracking financial transactions,
designed for personal or business use. Users can add deposits, make payments, and generate various reports based on their
transaction history.



***Main Menu***

When start the application, the main menu will display these options:
   
- D: Add Deposit: Manually add incoming funds. (date, time, description, vendor, amount )
-	P: Make Payment: Manually add expenses or payments made. (date, time, description, vendor, amount )
-	L: Ledger: View filtered transaction options 
- X: Exit: Close the application.


![Screenshot of Home Screen](images/mainmenu.png)


***Ledger Screen Options***

When viewing the ledger, the following options will display:
-	A: View All Transactions: Displays all tracked transactions.
-	D: View Deposits: Displays all incoming finances.
-	P: View Payments: Displays only expenses.
-	H: Home: Returns to main menu


![Screenshot of Home Screen](images/ledgermenu.png)

***Reports Screen Options***

In the Reports menu, you can select:
-	1: Month To Date: View transactions for the current month.
-	2: Previous Month: Review transactions from the last month.
  -	3: Year To Date: Analyze transactions from the beginning of the year.
   - 4: Previous Year: Look back at transactions from last year.
-	5: Search By Vendor: Find transactions related to a specific vendor.
-	6: Return to Ledger: Go back to the ledger menu.


![Screenshot of Home Screen](images/reportsmenu.png)


***Interesting Piece of Code:***

![Screenshot of Home Screen](images/interestingcode.png)

toCSVString Method:

- This method creates a string representation of a transaction, where each piece of information is separated by 
a pipe character (|). This format helps with organization and makes it easy to read.

***String.format Method:***

- This method creates a formatted string that uses placeholders for the output appearance.

***What are Placeholders:***

Placeholders are special symbols in the format string that tell the program where to insert the actual values. In this 
particular code example, the placeholders are: ***%s*** and ***%.2f***

The %s placeholder is used for string values. In this application, it will be replaced by:

- transactionDate

- transactionTime

- transactionDescription

- transactionVendor

The %.2f placeholder is used for floating-point numbers. The .2 means that the number will have 2 digits after the decimal 
point. In this application, it will be replaced by:

- transactionAmount

How the transaction details will display:

- Date: 2023-12-09
- Time: 11:10
- Description: Rental Car Payment
- Vendor: Turo
- Amount: 400.00

***Example of Transaction Display:***


![Screenshot of Home Screen](images/transactiondetails.png)





