package com.jackljones.www.basicBankAccountSimulator;

import com.jackljones.www.basicBankAccountSimulator.models.Account;
import com.jackljones.www.basicBankAccountSimulator.models.User;
import com.jackljones.www.basicBankAccountSimulator.utilities.AccountHelper;
import com.jackljones.www.basicBankAccountSimulator.utilities.DatabaseHelper;
import com.jackljones.www.basicBankAccountSimulator.utilities.MiscUtilities;
import com.jackljones.www.basicBankAccountSimulator.utilities.ScannerInputHandler;
import com.jackljones.www.basicBankAccountSimulator.utilities.UserHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import org.sqlite.SQLiteException;

/**
 * The App class is the main class of the program.
 */
public class App {

    private static Account selectedAccount = null;
    private static DatabaseHelper db = null;

    /**
     * The main function of the program.
     *
     */
    public static void main(String[] args) throws SQLException {
        String[] options = {
                "Create a new account",
                "Deposit money",
                "Check balance",
                "Withdraw money"
        };

        try (Scanner s = new Scanner(System.in)) {
            ScannerInputHandler sc = new ScannerInputHandler(s);
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                if (conn != null) {
                    db = new DatabaseHelper(conn);
                    while (true) {
                        // If this.accountNumber is not empty, remove the create account option
                        if (selectedAccount != null) {
                            options = MiscUtilities.removeElementFromArray(options, "Create a new account");
                        }

                        String selectedOption = MiscUtilities.getMenuOption(options, sc);

                        if (selectedOption == null) {
                            System.out.println("Exiting System.");
                            return;
                        }

                        switch (selectedOption) {
                            case "Create a new account":
                                createANewAccountMenu(sc, db);
                                break;
                            case "Deposit money":
                                depositMoneyMenu(sc, db);
                                break;
                            case "Check balance":
                                checkBalanceMenu(sc, db);
                                break;
                            case "Withdraw money":
                                withdrawMoneyMenu(sc, db);
                                break;
                            default:
                                System.out.println("Not yet supported");
                                break;
                        }
                    }
                }
            } catch (SQLiteException sqle) {
                System.err.println("Error connecting to the database");
                System.err.println(sqle.getMessage());
                sqle.printStackTrace();
            }

        }

    }

    /**
     * The createANewAccountMenu function is called when the user selects the
     * "Create a new account" option.
     *
     * @param sc a Scanner object
     * @param db a DatabaseHelper object
     */
    private static void createANewAccountMenu(ScannerInputHandler sc, DatabaseHelper db) {
        if (db == null) {
            throw new IllegalArgumentException("DatabaseHelper cannot be null");
        }

        System.out.println("Creating a new account");

        String firstName = UserHelper.getValidName(sc, "Enter your first name: ");
        String surname = UserHelper.getValidName(sc, "Enter your surname: ");

        String accountNumber = AccountHelper.generateAccountNumber();

        System.out.println("Please make note of your account numebr: " + accountNumber);

        int accountBalancePence = 500 * 100; // £ 500

        User user = new User(firstName, surname);

        Account account = new Account(accountNumber, accountBalancePence, user);

        db.insertAccount(account);

        App.selectedAccount = account;

        return;
    }

    /**
     * The ensureUserIsLoggedIn function checks if the user is logged in. If the
     * user
     * is not logged in, the function will prompt the user to log in.
     *
     * @param sc a Scanner object
     * @param db a DatabaseHelper object
     * @return true if the user is logged in, false otherwise.
     */
    private static boolean ensureUserIsLoggedIn(ScannerInputHandler sc, DatabaseHelper db) {
        if (selectedAccount != null) {
            return true;
        }

        Account account = AccountHelper.logUserIn(sc, db);
        if (account == null) {
            System.out.println("Could not log in.");
            return false;
        }
        selectedAccount = account;
        return true;

    }

    /**
     * The depositMoneyMenu function is called when the user selects the "Deposit
     * money" option.
     * 
     * @param sc A Scanner object
     * @param db A DatabaseHelper object
     */
    private static void depositMoneyMenu(ScannerInputHandler sc, DatabaseHelper db) {

        boolean isLoggedIn = ensureUserIsLoggedIn(sc, db);

        if (!isLoggedIn) {
            return;
        }

        int depositAmountPence = MiscUtilities.getMoneyInPenceFromUser(sc,
                "Enter the amount you would like to deposit: ");
        int currentBalancePence = selectedAccount.getBalancePence();

        int newBalancePence = currentBalancePence + depositAmountPence;

        selectedAccount.setBalancePence(newBalancePence);

        db.updateAccountBalancePence(selectedAccount);
    }

    /**
     * The checkBalanceMenu function is called when the user selects the "Check
     * balance" option.
     * 
     * @param sc A Scanner object
     * @param db A DatabaseHelper object
     */
    private static void checkBalanceMenu(ScannerInputHandler sc, DatabaseHelper db) {
        boolean isLoggedIn = ensureUserIsLoggedIn(sc, db);

        if (!isLoggedIn) {
            return;
        }

        int balancePence = selectedAccount.getBalancePence();
        System.out.println("Your balance is: " + MiscUtilities.getMoneyStringFromPence(balancePence));
    }

    /**
     * The withdrawMoneyMenu function is called when the user selects the "Withdraw
     * money" option.
     * 
     * @param sc A Scanner object
     * @param db A DatabaseHelper object
     */
    private static void withdrawMoneyMenu(ScannerInputHandler sc, DatabaseHelper db) {
        boolean isLoggedIn = ensureUserIsLoggedIn(sc, db);

        if (!isLoggedIn) {
            return;
        }

        int amountToWithdrawPence = MiscUtilities.getMoneyInPenceFromUser(sc,
                "Enter the amount you would like to withdraw: ");
        String moneyString = MiscUtilities.getMoneyStringFromPence(amountToWithdrawPence);

        System.out.printf("Are you sure you want to withdraw %s\n", moneyString);

        String[] options = {
                "Yes",
                "No"
        };

        String selectedOption = MiscUtilities.getMenuOption(options, sc);

        if (selectedOption == null) {
            System.out.println("Exiting System.");
            return;
        }

        if (selectedOption.equals("No")) {
            System.out.println("Aborting withdrawl");
            return;
        }

        int currentBalancePence = selectedAccount.getBalancePence();
        int newBalancePence = currentBalancePence - amountToWithdrawPence;

        if (newBalancePence < 0) {
            System.out.println("You do not have enough money to withdraw that amount");
            System.out.println("Performing this action would put your account into overdraft");
            System.out.println("Are you sure you want to continue?");

            selectedOption = MiscUtilities.getMenuOption(options, sc);

            if (selectedOption == null) {
                System.out.println("Exiting System.");
                return;
            }

            if (selectedOption.equals("No")) {
                System.out.println("Aborting withdrawl");
                return;
            }
        }

        System.out.println("Withdrawing money");
        selectedAccount.setBalancePence(newBalancePence);

        db.updateAccountBalancePence(selectedAccount);

        System.out.println("Money withdrawn successfully");
        System.out.printf("Your new balance is: %s\n", MiscUtilities.getMoneyStringFromPence(newBalancePence));

    }

}
