package com.jackljones.www.basicBankAccountSimulator.models;

/**
 * The Account class represents a bank account.
 * It stores information such as the account number, balance in pence, and the
 * associated user.
 */
public class Account {
    private String accountNumber;
    private int balancePence;
    private User user;

    /**
     * Constructor for the Account class.
     * 
     * @param accountNumber a string representing the unique account number
     * @param balancePence  an integer representing the balance in pence
     * @param user          a User object representing the account holder
     */
    public Account(String accountNumber, int balancePence, User user) {
        this.accountNumber = accountNumber;
        this.balancePence = balancePence;
        this.user = user;
    }

    /**
     * Getter for the account number.
     * 
     * @return the account number as a string
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Getter for the balance in pence.
     * 
     * @return the balance in pence as an integer
     */
    public int getBalancePence() {
        return balancePence;
    }

    /**
     * Setter for the balance in pence.
     * 
     * @param balancePence an integer representing the balance in pence
     */
    public void setBalancePence(int balancePence) {
        this.balancePence = balancePence;
    }

    /**
     * Getter for the balance in pounds.
     * 
     * @return the balance in pounds as a double
     */
    public double getBalance() {
        return balancePence / 100.0;
    }

    /**
     * Getter for the associated user.
     * 
     * @return the associated User object
     */
    public User getUser() {
        return user;
    }

    /**
     * An overridden toString method for the Account class.
     * 
     * @return a string representation of the Account object
     */
    @Override
    public String toString() {
        String forename = user.getForename();
        String surname = user.getSurname();

        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balancePence=" + balancePence +
                ", user=" + forename + " " + surname +
                '}';
    }

    /**
     * A method to return the account details as a comma separated string.
     * 
     * @return a string representing the account details as a comma separated string
     */
    public String toCommaSeperated() {
        String forename = user.getForename();
        String surname = user.getSurname();

        return accountNumber + "," + balancePence + "," + forename + "," + surname;
    }

}
