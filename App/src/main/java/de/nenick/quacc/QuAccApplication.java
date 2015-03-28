package de.nenick.quacc;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import de.nenick.quacc.core.dagger.DatabaseModule;
import de.nenick.quacc.dagger.AndroidModule;
import de.nenick.quacc.dagger.CoreModule;
import de.nenick.quacc.dagger.DaggerSupport;

public class QuAccApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerSupport.init(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(this),
                new CoreModule(),
                new DatabaseModule()
        );
    }
}
