package de.nenick.quacc.core;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import de.nenick.quacc.core.dagger.AndroidModule;
import de.nenick.quacc.core.dagger.DatabaseModuleMocks;
import de.nenick.quacc.dagger.DaggerSupport;

public class TestApplication extends Application {

    public static DatabaseModuleMocks databaseModuleMocks;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerSupport.init(getModules().toArray());
    }

    protected List<Object> getModules() {
        databaseModuleMocks = new DatabaseModuleMocks();
        return Arrays.asList(
                new AndroidModule(this),
                databaseModuleMocks
        );
    }
}
