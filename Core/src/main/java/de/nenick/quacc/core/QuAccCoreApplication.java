package de.nenick.quacc.core;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import de.nenick.quacc.core.dagger.AndroidModule;
import de.nenick.quacc.core.dagger.DaggerSupport;
import de.nenick.quacc.core.dagger.DatabaseModule;

public class QuAccCoreApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerSupport.init(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(this),
                new DatabaseModule()
        );
    }
}
