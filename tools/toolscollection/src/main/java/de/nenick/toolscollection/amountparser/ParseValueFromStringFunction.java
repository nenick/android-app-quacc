package de.nenick.toolscollection.amountparser;

/**
 * Parse different bookingEntries amount patterns to a 2 decimal places based integer amount (1 = 100).
 * <p/>
 * You should first check if the {@link AmountFromStringResult#report} is {@link AmountFromStringResult.ParseResult#Successful} or handle the error.
 * <p/>
 * The bookingEntries amount is valid if it match on of the following patterns:
 * - a positive natural number (e.g. "42")
 * - a positive real number with 1 or 2 numbers of digits after the decimal comma (e.g. ",34", "42,3", "42,31")
 */
public class ParseValueFromStringFunction {

    public static final String TWO_DECIMAL_PLACES = "00";

    private static final int MINIMAL_PART_LENGTH_AFTER_COMMA = 2;
    private static final int MINIMAL_PART_LENGTH_BEFOR_COMMA = 1;
    private static final int EXPECTED_DECIMAL_PARTS = 2;
    private static final int BEFORE_COMMA = 0;
    private static final int AFTER_COMMA = 1;
    private static final int NO_COMMA_FOUND = -1;

    public static AmountFromStringResult apply(String value) {
        value = value.replaceAll(" ", "");
        if (value.isEmpty()) {
            return new AmountFromStringResult(AmountFromStringResult.ParseResult.EmptyInput);
        }

        if (containsInvalidChars(value)) {
            return new AmountFromStringResult(AmountFromStringResult.ParseResult.InvalidChar);
        }

        if (isNaturalNumber(value)) {
            return parseValue(value + TWO_DECIMAL_PLACES);
        }

        if (isValidDecimalNumberFormat(value)) {
            return parseValue(value.replace(AmountFromStringResult.DECIMAL_DELIMITER, ""));
        }

        return new AmountFromStringResult(AmountFromStringResult.ParseResult.InvalidFormat);
    }

    private static boolean containsInvalidChars(String valueString) {
        return !valueString.matches("[" + AmountFromStringResult.ACCEPTED_CHARS + "]*");
    }

    private static AmountFromStringResult parseValue(final String valueString) {
        try {
            int parseResult = Integer.parseInt(valueString);
            if (parseResult == 0) {
                return new AmountFromStringResult(AmountFromStringResult.ParseResult.ZeroValue);
            } else {
                return new AmountFromStringResult(parseResult);
            }
        } catch (RuntimeException e) {
            return new AmountFromStringResult(AmountFromStringResult.ParseResult.UnknownError);
        }
    }

    private static boolean isValidDecimalNumberFormat(String valueString) {
        String[] valuePart = valueString.split(AmountFromStringResult.DECIMAL_DELIMITER);
        if (valuePart.length != EXPECTED_DECIMAL_PARTS) {
            return false;
        } else if (valuePart[BEFORE_COMMA].length() < MINIMAL_PART_LENGTH_BEFOR_COMMA) {
            return false;
        } else if (valuePart[AFTER_COMMA].length() != MINIMAL_PART_LENGTH_AFTER_COMMA) {
            return false;
        }
        return true;
    }

    private static boolean isNaturalNumber(String valueString) {
        int indexOfComma = valueString.indexOf(AmountFromStringResult.DECIMAL_DELIMITER);
        return indexOfComma == NO_COMMA_FOUND;
    }

}
