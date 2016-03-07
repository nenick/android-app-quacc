package de.nenick.toolscollection.amountparser;

public class AmountFromStringResult {

    public static final String DECIMAL_DELIMITER = ",";
    public static final String ACCEPTED_CHARS = "0-9" + DECIMAL_DELIMITER;

    public enum ParseResult {

        /** Amount could be parsed with a valid result */
        Successful,

        /** Zero amount may be an identifier for a parsing error it was just zero. */
        ZeroValue,

        /** For allowed chars see {@link #ACCEPTED_CHARS} */
        InvalidChar,

        /** Amount format could not be interpreted */
        InvalidFormat,

        /** Amount was empty after trim */
        EmptyInput,

        /** Unknown error, please report an example input as issue. */
        UnknownError
    }

    public final int amount;
    public final ParseResult report;

    public AmountFromStringResult(int parseResult) {
        amount = parseResult;
        report = ParseResult.Successful;
    }

    public AmountFromStringResult(ParseResult parseResult) {
        amount = 0;
        report = parseResult;
    }
}
