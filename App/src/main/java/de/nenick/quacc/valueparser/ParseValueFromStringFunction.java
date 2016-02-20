package de.nenick.quacc.valueparser;

import org.androidannotations.annotations.EBean;

/**
 * Parse different bookingEntries amount patterns to a 2 decimal places based integer amount (1 = 100).
 * <p/>
 * You should first check if the {@link Result#report} is {@link ParseResult#Successful} or handle the error.
 * <p/>
 * The bookingEntries amount is valid if it match on of the following patterns:
 * - a positive natural number (e.g. "42")
 * - a positive real number with 1 or 2 numbers of digits after the decimal comma (e.g. ",34", "42,3", "42,31")
 */
@EBean
public class ParseValueFromStringFunction {

    public static final String TWO_DECIMAL_PLACES = "00";

    public enum ParseResult {
        Successful,
        ZeroValue, InvalidChar, InvalidFormat, EmptyInput, UnknownError
    }

    private static final String DECIMAL_DELIMITER = ",";
    private static final String ACCEPTED_CHARS = "0-9" + DECIMAL_DELIMITER;

    private static final int MINIMAL_PART_LENGTH_AFTER_COMMA = 2;
    private static final int MINIMAL_PART_LENGTH_BEFOR_COMMA = 1;
    private static final int EXPECTED_DECIMAL_PARTS = 2;
    private static final int BEFORE_COMMA = 0;
    private static final int AFTER_COMMA = 1;
    private static final int NO_COMMA_FOUND = -1;

    public Result apply(String value) {
        value = value.replaceAll(" ", "");
        if (value.isEmpty()) {
            return new Result(ParseResult.EmptyInput);
        }

        if (containsInvalidChars(value)) {
            return new Result(ParseResult.InvalidChar);
        }

        if (isNaturalNumber(value)) {
            return parseValue(value + TWO_DECIMAL_PLACES);
        }

        if (isValidDecimalNumberFormat(value)) {
            return parseValue(value.replace(DECIMAL_DELIMITER, ""));
        }

        return new Result(ParseResult.InvalidFormat);
    }

    private boolean containsInvalidChars(String valueString) {
        return !valueString.matches("[" + ACCEPTED_CHARS + "]*");
    }

    private Result parseValue(final String valueString) {
        try {
            int parseResult = Integer.parseInt(valueString);
            if (parseResult == 0) {
                return new Result(ParseResult.ZeroValue);
            } else {
                return new Result(parseResult);
            }
        } catch (RuntimeException e) {
            return new Result(ParseResult.UnknownError);
        }
    }

    private boolean isValidDecimalNumberFormat(String valueString) {
        String[] valuePart = valueString.split(DECIMAL_DELIMITER);
        if (valuePart.length != EXPECTED_DECIMAL_PARTS) {
            return false;
        } else if (valuePart[BEFORE_COMMA].length() < MINIMAL_PART_LENGTH_BEFOR_COMMA) {
            return false;
        } else if (valuePart[AFTER_COMMA].length() != MINIMAL_PART_LENGTH_AFTER_COMMA) {
            return false;
        }
        return true;
    }

    private boolean isNaturalNumber(String valueString) {
        int indexOfComma = valueString.indexOf(DECIMAL_DELIMITER);
        return indexOfComma == NO_COMMA_FOUND;
    }

    public static class Result {
        public final int value;
        public final ParseResult report;

        public Result(int parseResult) {
            value = parseResult;
            report = ParseResult.Successful;
        }

        public Result(ParseResult parseResult) {
            value = 0;
            report = parseResult;
        }
    }
}
