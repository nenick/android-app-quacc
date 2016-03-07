package de.nenick.toolscollection.amountparser;

/**
 * String handling for amount values.
 */
public class AmountParser {

    public static String asString(int amount) {
        return ParseValueFromIntegerFunction.apply(amount);
    }

    public static AmountFromStringResult asInteger(String amount) {
        return ParseValueFromStringFunction.apply(amount);
    }
}
