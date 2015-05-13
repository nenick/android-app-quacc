package de.nenick.quacc.core.accounting;

import org.junit.Test;

import de.nenick.quacc.core.accounting.ParseAccountingValueUc.ParseResult;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseAccountingValueUcTest {

    ParseAccountingValueUc parseAccountingValueUc = new ParseAccountingValueUc();

    ParseAccountingValueUc.Result result;

    @Test
    public void shouldParsePositiveNaturalNumbers() {
        result = parseAccountingValueUc.apply("1234");
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.value).isEqualTo(123400);
    }

    @Test
    public void shouldParsePositiveRealNumbersWithDotLessThan10Cents() {
        result = parseAccountingValueUc.apply("0.03");
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.value).isEqualTo(3);
    }

    @Test
    public void shouldParsePositiveRealNumbersWithDotLessThan100Cents() {
        result = parseAccountingValueUc.apply("0.53");
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.value).isEqualTo(53);
    }

    @Test
    public void shouldParsePositiveRealNumbersWithDotGreaterThan99Cents() {
        result = parseAccountingValueUc.apply("24.53");
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.value).isEqualTo(2453);
    }

    @Test
    public void shouldParsePositiveRealNumbersWithoutPreDecimalPointPosition() {
        result = parseAccountingValueUc.apply(".30");
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.value).isEqualTo(30);

        result = parseAccountingValueUc.apply(",53");
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.value).isEqualTo(53);
    }

    @Test
    public void shouldParsePositiveRealNumbersWithoutPostDecimalPointPosition() {
        result = parseAccountingValueUc.apply("3.");
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.value).isEqualTo(300);

        result = parseAccountingValueUc.apply("53,");
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.value).isEqualTo(5300);
    }

    @Test
    public void shouldParsePositiveRealNumbersWithDotSingleNumber() {
        result = parseAccountingValueUc.apply("0.8");
        assertThat(result.report).isEqualTo(ParseResult.Successful);
        assertThat(result.value).isEqualTo(80);
    }

    @Test
    public void shouldReportErrorMixDotAndComma() {
        result = parseAccountingValueUc.apply("1.000,2");
        assertThat(result.report).isEqualTo(ParseResult.DotAndCommaMix);

        result = parseAccountingValueUc.apply("1,000.2");
        assertThat(result.report).isEqualTo(ParseResult.DotAndCommaMix);
    }

    @Test
    public void shouldReportErrorNegativeNumberForNaturalNumbers() {
        result = parseAccountingValueUc.apply("-1");
        assertThat(result.report).isEqualTo(ParseResult.NegativeNumber);
    }

    @Test
    public void shouldReportErrorNegativeNumberForRealNumbers() {
        result = parseAccountingValueUc.apply("-1.32");
        assertThat(result.report).isEqualTo(ParseResult.NegativeNumber);

        result = parseAccountingValueUc.apply("-1,32");
        assertThat(result.report).isEqualTo(ParseResult.NegativeNumber);
    }

    @Test
    public void shouldReportErrorZeroValueForNaturalNumbers() {
        result = parseAccountingValueUc.apply("0");
        assertThat(result.report).isEqualTo(ParseResult.ZeroValue);
    }

    @Test
    public void shouldReportErrorZeroValueForRealNumbers() {
        result = parseAccountingValueUc.apply("0.00");
        assertThat(result.report).isEqualTo(ParseResult.ZeroValue);

        result = parseAccountingValueUc.apply("0,00");
        assertThat(result.report).isEqualTo(ParseResult.ZeroValue);
    }

    @Test
    public void shouldReportErrorNoValidNumberForOnlyDecimalPointValue() {
        result = parseAccountingValueUc.apply(".");
        assertThat(result.report).isEqualTo(ParseResult.NoValidNumber);

        result = parseAccountingValueUc.apply(",");
        assertThat(result.report).isEqualTo(ParseResult.NoValidNumber);
    }

    @Test
    public void shouldReportErrorNoValidNumber() {
        result = parseAccountingValueUc.apply("Z");
        assertThat(result.report).isEqualTo(ParseResult.NoValidNumber);

        result = parseAccountingValueUc.apply("A.33");
        assertThat(result.report).isEqualTo(ParseResult.NoValidNumber);
    }

    @Test
    public void shouldReportNullOrEmpty() {
        result = parseAccountingValueUc.apply("");
        assertThat(result.report).isEqualTo(ParseResult.NullOrEmpty);

        result = parseAccountingValueUc.apply(null);
        assertThat(result.report).isEqualTo(ParseResult.NullOrEmpty);
    }

    @Test
    public void shouldRemoveSpaces() {
        result = parseAccountingValueUc.apply(" 5  0. 33 ");
        assertThat(result.value).isEqualTo(5033);
    }
}