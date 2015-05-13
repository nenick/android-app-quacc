package de.nenick.quacc.core.accounting;

import org.androidannotations.annotations.EBean;

/**
 * Parse different accounting value patterns to a 2 decimal places based integer value (1 = 100).
 * <p/>
 * You should first check if the {@link Result#report} is {@link ParseResult#Successful} or handle the error.
 * <p/>
 * The accounting value is valid if it match on of the following patterns:
 * - a positive natural number (e.g. "42")
 * - a positive real number with 1 or 2 numbers of digits after the decimal point (e.g. ".34", "42.3", "42.31")
 * - a positive real number with 1 or 2 numbers of digits after the decimal comma (e.g. ",34", "42,3", "42,31")
 */
@EBean
public class ParseAccountingValueUc {

    public Result apply(String value) {
        if (value == null) {
            return new Result(ParseResult.NullOrEmpty);
        }
        value = value.replaceAll(" ", "");
        if (value.isEmpty()) {
            return new Result(ParseResult.NullOrEmpty);
        }

        // negative accounting should be applied with special type not with negative values
        if (isFirstDigitMinusSign(value)) {
            return new Result(ParseResult.NegativeNumber);
        }

        // reject if value contains non number digits expect ',' and '.'
        if (!value.matches("[0-9,\\.]*")) {
            return new Result(ParseResult.NoValidNumber);
        }

        return parseValue(value);
    }

    private Result parseValue(final String originalValue) {
        boolean containsDot = originalValue.contains(".");
        boolean containsComma = originalValue.contains(",");

        // there are to many variations for handle combinations of dot and comma, maybe later
        if (containsDot && containsComma) {
            return new Result(ParseResult.DotAndCommaMix);
        }

        boolean isDecimal = containsDot || containsComma;

        // reject if value is only a decimal delimiter
        if (isDecimal && originalValue.length() == 1) {
            return new Result(ParseResult.NoValidNumber);
        }

        // parsing works not when the value contains dot or comma
        String value = originalValue;
        value = value.replace(".", "");
        value = value.replace(",", "");

        int parseResult;
        try {
            parseResult = Integer.parseInt(value);
        } catch (RuntimeException e) {
            return new Result(ParseResult.UnknownError);
        }

        // zero is not an accepted value perhaps it's a unknown error
        if (parseResult == 0) {
            return new Result(ParseResult.ZeroValue);
        }

        // add decimal place for natural numbers
        if (isNaturalNumber(containsDot, containsComma)) {
            parseResult *= 100;
        }

        // add second decimal place for real number when missing (e.g. "45.4")
        if (isOnlyOneNumberAfterDecimalPoint(originalValue)) {
            parseResult *= 10;
        }

        // add decimal places for real number when missing (e.g. "45.")
        if (isNoNumberAfterDecimalPoint(originalValue)) {
            parseResult *= 100;
        }

        return new Result(parseResult);
    }

    private boolean isFirstDigitMinusSign(String value) {
        return value.indexOf("-") == 0;
    }

    private boolean isNaturalNumber(boolean containsDots, boolean containsCommas) {
        return !containsDots && !containsCommas;
    }

    private boolean isOnlyOneNumberAfterDecimalPoint(String value) {
        final int PRE_LAST_DIGIT = value.length() - 2;
        boolean foundDot = value.indexOf(".") == PRE_LAST_DIGIT;
        boolean foundComma = value.indexOf(",") == PRE_LAST_DIGIT;
        return foundDot || foundComma;
    }

    private boolean isNoNumberAfterDecimalPoint(String value) {
        final int LAST_DIGIT = value.length() - 1;
        boolean foundDot = value.indexOf(".") == LAST_DIGIT;
        boolean foundComma = value.indexOf(",") == LAST_DIGIT;
        return foundDot || foundComma;
    }

    public enum ParseResult {
        Successful,
        NegativeNumber, ZeroValue, DotAndCommaMix, NoValidNumber, NullOrEmpty, UnknownError
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
