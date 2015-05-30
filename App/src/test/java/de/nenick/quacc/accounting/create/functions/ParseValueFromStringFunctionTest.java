package de.nenick.quacc.accounting.create.functions;

import org.junit.Test;

import de.nenick.quacc.accounting.create.functions.ParseValueFromStringFunction.ParseResult;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseValueFromStringFunctionTest {

    ParseValueFromStringFunction parseValueFromStringFunction = new ParseValueFromStringFunction();

    ParseValueFromStringFunction.Result result;

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
        result = parseValueFromStringFunction.apply(input);
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.value).isEqualTo(expected);
    }

    private void parseAndExpect(String input, ParseResult expected) {
        result = parseValueFromStringFunction.apply(input);
        assertThat(result.report).isEqualTo(expected);
    }
}