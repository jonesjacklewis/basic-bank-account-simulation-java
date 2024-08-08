package com.jackljones.www.basicBankAccountSimulator.utilities;

/**
 * The Validation class provides methods to validate user input.
 */
public class Validation {

    /**
     * Checks if the given input is a valid integer number.
     *
     * @param input the input string to be checked.
     * @return true if the input is a valid integer number, false otherwise
     */
    public static boolean isIntegerNumber(String input) {
        if (input.contains(".")) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Checks if the given input lies in a range between a minimum and a meximum
     * 
     * @param value        the integer value to be checked.
     * @param minimumValue the minimum integer value to be checked against.
     * @param maximumValue the maximum integer value to be checked against.
     * @return true if the value is in range, false otherwise.
     */
    public static boolean integerIsInRange(int value, int minimumValue, int maximumValue) {
        if (value < minimumValue) {
            return false;
        }

        if (value > maximumValue) {
            return false;
        }

        return true;
    }

    /**
     * Checks if a string contains only alphabetic characters and approved
     * characters.
     * 
     * @param toTest             the string to be checked.
     * @param approvedCharacters an array of approved characters.
     * @return true if the string contains only alphabetic characters and approved
     *         characters, false otherwise.
     */
    public static boolean stringContainsAlphaAndApprovedCharacters(String toTest, Character[] approvedCharacters) {

        if (approvedCharacters == null) {
            approvedCharacters = new Character[0];
        }

        boolean isAlphabetic, isApproved;

        for (Character c : toTest.toCharArray()) {
            isAlphabetic = Character.isAlphabetic(c);
            isApproved = MiscUtilities.arrayContainsValue(approvedCharacters, c);

            if (!(isAlphabetic || isApproved)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if an account number is valid. An account number is valid if it is an
     * 8 digit number.
     * 
     * @param accountNumber a string representing the account number to be checked.
     * @return true if the account number is valid, false otherwise.
     * 
     */
    public static boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber == null) {
            return false;
        }

        if (accountNumber.isEmpty()) {
            return false;
        }

        if (accountNumber.length() != 8) {
            return false;
        }

        if (!isIntegerNumber(accountNumber)) {
            return false;
        }

        return true;
    }

    /**
     * Checks if a string is a valid money value. A valid money value is a number
     * with up to 2 decimal places.
     * 
     * @param input a string representing the money value to be checked.
     * @return true if the money value is valid, false otherwise.
     */
    public static boolean isValidMoneyValueByRegex(String input) {
        return input.matches("^-?\\d+(\\.\\d{1,2})?$");
    }

}
