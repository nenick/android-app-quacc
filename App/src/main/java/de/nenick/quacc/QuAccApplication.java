package de.nenick.quacc;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import de.nenick.quacc.daggersupport.AndroidModule;
import de.nenick.quacc.daggersupport.CoreModule;
import de.nenick.quacc.daggersupport.DaggerSupport;

public class QuAccApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerSupport.init(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(this),
                new CoreModule()
        );
    }
}
