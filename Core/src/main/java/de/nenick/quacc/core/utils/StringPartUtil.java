package de.nenick.quacc.core.utils;


public abstract class StringPartUtil {

    public static String removePartWithLength(String source, int start, int length) {
        return source.replace(source.substring(start, start + length), "").replaceAll("  ", " ").trim();
    }

}
