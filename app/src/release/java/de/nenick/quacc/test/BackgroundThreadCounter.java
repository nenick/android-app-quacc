package de.nenick.quacc.test;

public class BackgroundThreadCounter {

    private static BackgroundThreadCounter instance;

    public static BackgroundThreadCounter instance() {
        if(instance == null) {
            instance = new BackgroundThreadCounter();
        }
        return instance;
    }

    private BackgroundThreadCounter() {
        // just hide constructor
    }

    public static void increment() {

    }

    public static void decrement() {

    }
}
