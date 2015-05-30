package de.nenick.quacc.accounting.list.functions;

import org.androidannotations.annotations.EBean;

@EBean
public class ParseValueFromIntegerFunction {

    public String apply(int value) {
        String valueString = String.valueOf(value);
        if (value < 10) {
            valueString = "0,0" + valueString;
        } else if (value < 100) {
            valueString = "0," + valueString;
        } else {
            String decimal = valueString.substring(valueString.length() - 2, valueString.length());
            valueString = valueString.substring(0, valueString.length() - 2) + "," + decimal;
        }
        return valueString;
    }
}
