package de.nenick.quacc.core.i18n;

import org.androidannotations.annotations.EBean;

@EBean
public class MonthTranslator {

    public String translate(int month) {
        switch (month) {
            case 1:
                return "Januar";
            case 2:
                return "Februar";
            case 3:
                return "März";
            case 4:
                return "April";
            case 5:
                return "Mai";
            case 6:
                return "Juni";
            case 7:
                return "Juli";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "Oktober";
            case 11:
                return "November";
            case 12:
                return "Dezember";
        }
        throw new IllegalStateException();

    }

    public int translate(String name) {
        if("Januar".equals(name)) {
            return 1;
        } else if ("Februar".equals(name)) {
            return 2;
        } else if ("März".equals(name)) {
            return 3;
        } else if ("April".equals(name)) {
            return 4;
        } else if ("Mai".equals(name)) {
            return 5;
        } else if ("Juni".equals(name)) {
            return 6;
        } else if ("Juli".equals(name)) {
            return 7;
        } else if ("August".equals(name)) {
            return 8;
        } else if ("September".equals(name)) {
            return 9;
        } else if ("Oktober".equals(name)) {
            return 10;
        } else if ("November".equals(name)) {
            return 11;
        } else if ("Dezember".equals(name)) {
            return 12;
        } else {
            throw new IllegalStateException();
        }
    }
}
