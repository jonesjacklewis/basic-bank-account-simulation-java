package com.jackljones.www.basicBankAccountSimulator.utilities;

import java.util.Scanner;

/**
 * The UserHelper class provides methods to assist with user input and
 * validation.
 */
public class UserHelper {

    // Constants
    // The minimum and maximum length of a name
    private static int MINIMUM_STRING_LENGTH = 2;
    private static int MAXIMUM_STRING_LENGTH = 50;

    // The approved characters for a name
    private static Character[] APPROVED_CHARACTERS = { '-', '\'', '.' };
    private static String APPROVED_CHARACTERS_CSV = MiscUtilities.arrayToCommaSeperatedString(APPROVED_CHARACTERS);

    /**
     * Prompts the user for a valid name.
     * 
     * @param sc     a Scanner object
     * @param prompt a string representing the prompt
     * @return a string representing the valid name
     */
    public static String getValidName(Scanner sc, String prompt) {
        String name = "";

        while (true) {

            System.out.println(prompt);

            if (!sc.hasNextLine()) {
                continue;
            }

            name = sc.nextLine();

            // Length checks
            if (!Validation.integerIsInRange(name.length(), MINIMUM_STRING_LENGTH, MAXIMUM_STRING_LENGTH)) {
                System.out.printf("A name must be between %d and %d letters", MINIMUM_STRING_LENGTH,
                        MAXIMUM_STRING_LENGTH);
                continue;
            }

            if (!Validation.stringContainsAlphaAndApprovedCharacters(name, APPROVED_CHARACTERS)) {
                System.err.printf("A name can only contain alphabetical characters and '%s'", APPROVED_CHARACTERS_CSV);
                continue;
            }

            return name;

        }
    }

}
