package de.nenick.quacc;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import java.io.IOException;
import java.util.logging.LogManager;

public class QuAccApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        try {
            LogManager.getLogManager().readConfiguration(getAssets().open("logging.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
