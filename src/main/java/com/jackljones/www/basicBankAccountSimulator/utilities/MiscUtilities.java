package com.jackljones.www.basicBankAccountSimulator.utilities;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The MiscUtilities class contains miscellaneous utility methods.
 */
public class MiscUtilities {

    /**
     * Displays the options in a generic array to the console as a numbered list.
     * 
     * @param menuOptions a generic array
     */
    private static <T> void displayOptions(T[] menuOptions) {
        if (menuOptions == null || menuOptions.length == 0) {
            return;
        }

        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ".) " + menuOptions[i]);
        }
    }

    /**
     * Displays the options in a generic array to the console as a numbered list and
     * prompts the user to select an option.
     * 
     * @param menuOptions a generic array
     * @param sc          a Scanner object
     * @return a value from the generic array menuOptions or null
     */
    public static <T> T getMenuOption(T[] menuOptions, Scanner sc) {
        if (menuOptions == null || menuOptions.length == 0) {
            return null;
        }

        displayOptions(menuOptions);

        while (true) {
            System.out.print("Enter the number of the option you would like to select, or 'exit' to quit: ");

            if (!sc.hasNextLine()) {
                continue;
            }

            String stringOption = sc.nextLine();
            stringOption = stringOption.trim();
            stringOption = stringOption.toLowerCase();

            if (stringOption.equals("exit")) {
                return null;
            }

            if (!Validation.isIntegerNumber(stringOption)) {
                System.out.println("Input must be a valid integer.");
                continue;
            }

            int option = Integer.parseInt(stringOption);

            if (!Validation.isIntegerInRange(option, 1, menuOptions.length)) {
                System.out.printf("Value must lie between 1 and %d\n", menuOptions.length);
                continue;
            }

            return menuOptions[option - 1];
        }

    }

    /**
     * Checks if a generic array contains a value.
     * 
     * @param array a generic array of values
     * @param value a value of the same type as the array
     * @return true if the array contains the value else false
     */
    public static <T> boolean arrayContainsValue(T[] array, T value) {
        if (array.length == 0) {
            return false;
        }
        return Arrays.asList(array).contains(value);
    }

    /**
     * Converts a generic array to a comma separated string.
     * 
     * @param array a generic array
     * @return a string representing the generic array
     */
    public static <T> String arrayToCommaSeperatedString(T[] array) {
        if (array == null || array.length == 0) {
            return "";
        }

        if (array.length == 1) {
            T t = array[0];

            // if its a string
            if (t instanceof String) {
                return (String) t;
            }

            return t.toString();

        }

        StringBuilder sb = new StringBuilder();

        for (T t : array) {
            // If T is a string, just append it
            sb.append(t);
            sb.append(',');
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    /**
     * Generates a random integer between the minimum and maximum values.
     * 
     * @param minimum the minimum value
     * @param maximum the maximum value
     * @return a random integer between the minimum and maximum values
     * @throws IllegalArgumentException if the minimum value is greater than the
     *                                  maximum value
     */
    public static int generateRandomInteger(int minimum, int maximum) {
        if (minimum > maximum) {
            throw new IllegalArgumentException("Minimum value must be less than the maximum value");
        }

        return ThreadLocalRandom.current().nextInt(minimum, maximum + 1);
    }

    /**
     * Removes an element from a generic array.
     * 
     * @param array   a generic array
     * @param element the element to remove
     * @return a generic array with the element removed
     */
    public static <T> T[] removeElementFromArray(T[] array, T element) {
        if (array == null || array.length == 0) {
            return array;
        }

        if (!arrayContainsValue(array, element)) {
            return array;
        }

        T[] newArray = Arrays.copyOf(array, array.length - 1);

        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                continue;
            }

            newArray[j] = array[i];
            j++;
        }

        return newArray;
    }

    /**
     * Prompts the user to enter a valid amount of money in pence.
     * 
     * @param sc     a Scanner object
     * @param prompt a string representing the prompt
     * @return an integer representing the amount of money in pence
     * @throws IllegalArgumentException if the Scanner object is null
     */
    public static int getMoneyInPenceFromUser(Scanner sc, String prompt) {
        if (sc == null) {
            throw new IllegalArgumentException("Scanner cannot be null");
        }

        while (true) {
            System.out.print(prompt);

            if (!sc.hasNextLine()) {
                continue;
            }

            String input = sc.nextLine();
            input = input.trim();
            input = input.replace(",", "");

            // if input contains a £, $, or € anywhere, remove it
            input = input.replaceAll("[£$€]", "");

            if (!Validation.isValidMoneyValueByRegex(input)) {
                System.out.println("Input must be a valid number with 2 decimal places.");
                continue;
            }

            input = input.replace(".", "");

            return Integer.parseInt(input);
        }
    }

    /**
     * Converts an integer representing money in pence to a string representing money
     * 
     * @param balancePence an integer representing money in pence
     * @return a string representing money
     */
    public static String getMoneyStringFromPence(int balancePence){
        double balancePounds = balancePence / 100.0;
        return String.format("£%.2f", balancePounds);
    }
}
