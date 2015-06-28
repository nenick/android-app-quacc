package de.nenick.quacc.speechrecognition;


public class WordToNumber {


    public static String intToText10(int x) {
        int y=x/10; /* nur Zehnerstelle */
        switch(y) {
            case 1:
                return "zehn";
            case 2:
                return "zwanzig";
            case 3:
                return "dreißig";
            case 4:
                return "vierzig";
            case 5:
                return "fünfzig";
            case 6:
                return "sechzig";
            case 7:
                return "siebzig";
            case 8:
                return "achtzig";
            case 9:
                return "neunzig";
            default:
                return "FEHLER";
        }
    }

}
