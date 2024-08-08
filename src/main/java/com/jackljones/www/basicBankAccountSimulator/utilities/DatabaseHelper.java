package com.jackljones.www.basicBankAccountSimulator.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jackljones.www.basicBankAccountSimulator.models.Account;
import com.jackljones.www.basicBankAccountSimulator.models.User;

/**
 * The DatabaseHelper class provides methods to interact with the SQLite
 * database.
 */
public class DatabaseHelper {
    private Connection conn;

    /**
     * Constructor for the DatabaseHelper class. Creates the necessary database
     * tables.
     * 
     * @param conn a Connection object representing the database connection
     */
    public DatabaseHelper(Connection conn) {
        this.conn = conn;

        createDatabaseTables(conn);
    }

    /**
     * Creates the necessary database tables if they do not already exist.
     * 
     * @param conn a Connection object representing the database connection
     */
    private static void createDatabaseTables(Connection conn) {
        createAccountTable(conn);
    }

    /**
     * Creates the account table if it does not already exist.
     * 
     * @param conn a Connection object representing the database connection
     */
    private static void createAccountTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS account (\n"
                + "	account_number TEXT PRIMARY KEY,\n"
                + "	balance_pence INTEGER NOT NULL,\n"
                + "	forename TEXT NOT NULL,\n"
                + "	surname TEXT NOT NULL\n"
                + ");";

        try {
            conn.createStatement().execute(sql);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Inserts an account into the database.
     * 
     * @param account an Account object representing the account to insert
     */
    public void insertAccount(Account account) {
        try {
            String sql = "INSERT OR IGNORE INTO account(account_number, balance_pence, forename, surname) VALUES(?,?,?,?)";
            String accountNumber = account.getAccountNumber();
            int balancePence = account.getBalancePence();

            User user = account.getUser();
            String forename = user.getForename();
            String surname = user.getSurname();

            // Make sure to parameterize the SQL query to prevent SQL injection
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNumber);
            pstmt.setInt(2, balancePence);
            pstmt.setString(3, forename);
            pstmt.setString(4, surname);

            pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Checks if an account number exists in the database.
     * 
     * @param accountNumber a string representing the account number to check
     * @return true if the account number exists, false otherwise
     */
    public boolean accountNumberExists(String accountNumber) {
        if (accountNumber == null) {
            return false;
        }

        if (accountNumber.isEmpty()) {
            return false;
        }

        try {
            String sql = "SELECT account_number FROM account WHERE account_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNumber);

            return pstmt.executeQuery().next();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves an account from the database.
     * 
     * @param accountNumber a string representing the account number to retrieve
     * @return an Account object representing the account or null.
     * @throws IllegalArgumentException if the account number does not exist
     */
    public Account getAccount(String accountNumber) {
        if (!accountNumberExists(accountNumber)) {
            throw new IllegalArgumentException("Account number does not exist");
        }

        try {
            String sql = "SELECT balance_pence, forename, surname FROM account WHERE account_number = ? LIMIT 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNumber);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int balancePence = rs.getInt("balance_pence");
                String forename = rs.getString("forename");
                String surname = rs.getString("surname");
                User user = new User(forename, surname);
                return new Account(accountNumber, balancePence, user);
            }
            return null;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Updates an account's balance in the database.
     * 
     * @param account an Account object representing the account to update
     * 
     * @throws IllegalArgumentException if the account number does not exist
     */
    public void updateAccountBalancePence(Account account) {
        if (!accountNumberExists(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number does not exist");
        }
        try {
            String sql = "UPDATE account SET balance_pence = ? WHERE account_number = ?";
            String accountNumber = account.getAccountNumber();
            int balancePence = account.getBalancePence();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, balancePence);
            pstmt.setString(2, accountNumber);

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
