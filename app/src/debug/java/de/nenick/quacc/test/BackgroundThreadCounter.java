package de.nenick.quacc.test;

import android.support.test.espresso.IdlingResource;

public class BackgroundThreadCounter implements IdlingResource {

    private static BackgroundThreadCounter instance;

    private int count = 0;
    private ResourceCallback callback;

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
        instance().count++;
    }

    public static void decrement() {
        instance().count--;
        if(instance.count < 0) throw new IllegalStateException();
        if(instance.count == 0 && instance.callback != null) instance.callback. onTransitionToIdle();
    }

    @Override public String getName() {
        return BackgroundThreadCounter.class.getSimpleName();
    }

    @Override public boolean isIdleNow() {
        return count == 0;
    }

    @Override public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.callback = resourceCallback;
    }
}
