package com.jackljones.www.basicBankAccountSimulator;

import com.jackljones.www.basicBankAccountSimulator.utilities.Validation;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidationTest {

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is null.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return false.
     */
    @Test
    public void isIntegerNumber_when_inputIsNull_returnFalse() {
        String input = null;
        boolean result = Validation.isIntegerNumber(input);
        assertFalse(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is an empty string.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return false.
     */
    @Test
    public void isIntegerNumber_when_inputIsEmpty_returnFalse() {
        String input = "";
        boolean result = Validation.isIntegerNumber(input);
        assertFalse(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is whitespace.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return false.
     */
    @Test
    public void isIntegerNumber_when_inputIsWhitespace_returnFalse() {
        String input = "    ";
        boolean result = Validation.isIntegerNumber(input);
        assertFalse(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input contains a period.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return false.
     */
    @Test
    public void isIntegerNumber_when_inputContainsPeriod_returnFalse() {
        String input = "123.45";
        boolean result = Validation.isIntegerNumber(input);
        assertFalse(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is not a number.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return false.
     */
    @Test
    public void isIntegerNumber_when_inputIsNotANumber_returnFalse() {
        String input = "abc";
        boolean result = Validation.isIntegerNumber(input);
        assertFalse(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is a number.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputIsANumber_returnTrue() {
        String input = "123";
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is a number with padding.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputIsNumberWithPadding_returnTrue() {
        String input = "  123  ";
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is a negative number.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputIsNegativeNumber_returnTrue() {
        String input = "-123";
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is a negative number with padding.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputIsNegativeNumberWithPadding_returnTrue() {
        String input = "  -123  ";
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is a positive number.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputIsZero_returnTrue() {
        String input = "0";
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is a negative zero.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputIsNegativeZero_returnTrue() {
        String input = "-0";
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is a positive zero.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputIsPositiveZero_returnTrue() {
        String input = "+0";
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is a positive number with a prepended plus sign.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputPrependedWithPlus_returnTrue() {
        String input = "+123";
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is a positive number with a prepended plus sign and padding.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputPrependedWithPlusAndPadding_returnTrue() {
        String input = "  +123  ";
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is the maximum integer value.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputIsMaxInteger_returnTrue() {
        String input = Integer.toString(Integer.MAX_VALUE);
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

    /**
     * Testing the isIntegerNumber method.
     * GIVEN: The input is the minimum integer value.
     * WHEN: The input is checked to see if it is an integer number.
     * THEN: The method should return true.
     */
    @Test
    public void isIntegerNumber_when_inputIsMinInteger_returnTrue() {
        String input = Integer.toString(Integer.MIN_VALUE);
        boolean result = Validation.isIntegerNumber(input);
        assertTrue(result);
    }

}
