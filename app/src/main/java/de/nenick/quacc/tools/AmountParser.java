package de.nenick.quacc.tools;

import org.androidannotations.annotations.EBean;

import de.nenick.toolscollection.amountparser.AmountFromStringResult;
import de.nenick.toolscollection.amountparser.ParseValueFromIntegerFunction;
import de.nenick.toolscollection.amountparser.ParseValueFromStringFunction;

/**
 * String handling for amount values.
 */
@EBean
public class AmountParser {

    public String asString(int amount) {
        return ParseValueFromIntegerFunction.apply(amount);
    }

    public AmountFromStringResult asInteger(String amount) {
        return new ParseValueFromStringFunction().apply(amount);
    }
}
