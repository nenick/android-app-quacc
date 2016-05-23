package de.nenick.toolscollection.amountparser;

import org.junit.Test;

import de.nenick.toolscollection.amountparser.AmountFromStringResult.ParseResult;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseValueFromStringFunctionTest {

    AmountFromStringResult result;

    @Test
    public void shouldParseNaturalNumbers() {
        parseAndExpect("1", 100);
        parseAndExpect("10", 1000);
        parseAndExpect("11", 1100);
        parseAndExpect("9999", 999900);
    }

    @Test
    public void shouldParseRealNumbers() {
        parseAndExpect("0,01", 1);
        parseAndExpect("0,20", 20);
        parseAndExpect("1,23", 123);
        parseAndExpect("5432,23", 543223);
    }

    @Test
    public void shouldRejectInvalidChar() {
        parseAndExpect("A", ParseResult.InvalidChar);
        parseAndExpect("-", ParseResult.InvalidChar);
        parseAndExpect(".", ParseResult.InvalidChar);
    }

    @Test
    public void shouldRejectInvalidFormat() {
        parseAndExpect("10,10,10", ParseResult.InvalidFormat);
        parseAndExpect("1,000", ParseResult.InvalidFormat);
        parseAndExpect(",11", ParseResult.InvalidFormat);
        parseAndExpect("0,1", ParseResult.InvalidFormat);
    }

    @Test
    public void shouldRejectEmptyValue() {
        parseAndExpect("", ParseResult.EmptyInput);
    }

    @Test
    public void shouldRejectZeroValue() {
        parseAndExpect("0", ParseResult.ZeroValue);
    }

    private void parseAndExpect(String input, int expected) {
        result = new ParseValueFromStringFunction().apply(input);
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.amount).isEqualTo(expected);
    }

    private void parseAndExpect(String input, ParseResult expected) {
        result = new ParseValueFromStringFunction().apply(input);
        assertThat(result.report).isEqualTo(expected);
    }
}