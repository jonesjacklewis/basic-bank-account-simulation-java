package com.jackljones.www.basicBankAccountSimulator.utilities;

import java.util.Scanner;

import com.jackljones.www.basicBankAccountSimulator.models.Account;

/**
 * The AccountHelper class contains helper methods for the Account class.
 */
public class AccountHelper {
    /**
     * Generates a random account number.
     * 
     * @return a string representing the account number
     */
    public static String generateAccountNumber() {
        // int array to hold 8 digits
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            sb.append(MiscUtilities.generateRandomInteger(1, 9));
        }

        return sb.toString();
    }

    /**
     * Logs a user into their account.
     * 
     * @param sc a Scanner object
     * @param db a DatabaseHelper object
     * @return an Account object
     */
    public static Account logUserIn(Scanner sc, DatabaseHelper db){
        if(db == null){
            System.out.println("Database not connected.");
            return null;
        }

        if(sc == null){
            System.out.println("Scanner not connected.");
            return null;
        }

        String accountNumber = getValidAccountNumber(sc);

        Account account = db.getAccount(accountNumber);

        if(account == null){
            System.out.println("Account not found.");
            return null;
        }

        return account;
    }

    /**
     * Prompts the user to enter a valid account number.
     * 
     * @param sc a Scanner object
     * @return a string representing the account number
     */
    private static String getValidAccountNumber(Scanner sc){
        if(sc == null){
            return "";
        }

        String accountNumber = "";

        do{
            System.out.println("Please enter your account number: ");
            accountNumber = sc.nextLine();
            accountNumber = accountNumber.trim();
        } while(!Validation.isValidAccountNumber(accountNumber));

        return accountNumber;
    }
}
