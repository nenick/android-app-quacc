package de.nenick.quacc;

import android.app.Application;

import java.io.IOException;
import java.util.logging.LogManager;

public class QuAccApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            LogManager.getLogManager().readConfiguration(getAssets().open("logging.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
