package com.jackljones.www.basicBankAccountSimulator.models;

/**
 * The User class represents a bank user.
 * It stores information such as the user's forename and surname.
 */
public class User {
    private String forename;
    private String surname;

    /**
     * Constructor for the User class.
     * 
     * @param forename a string representing the user's forename
     * @param surname  a string representing the user's surname
     */
    public User(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    /**
     * Getter for the user's forename.
     * 
     * @return the user's forename as a string
     */
    public String getForename() {
        return forename;
    }

    /**
     * Setter for the user's forename.
     * 
     * @param forename a string representing the user's forename
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Getter for the user's surname.
     * 
     * @return the user's surname as a string
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter for the user's surname.
     * 
     * @param surname a string representing the user's surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * An overridden toString method for the User class.
     * 
     * @return a string representation of the User object
     */
    @Override
    public String toString() {
        return "User{" +
                "forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    /**
     * A method to return the user's forename and surname as a comma separated
     * string.
     * 
     * @return the user's forename and surname as a comma separated string
     */
    public String toCommaSeperated() {
        return forename + "," + surname;
    }
}
