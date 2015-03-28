package de.nenick.quacc.core;

import java.util.Arrays;
import java.util.List;

import de.nenick.quacc.core.dagger.AndroidModule;
import de.nenick.quacc.core.dagger.DatabaseModuleMocks;

public class TestQuAccCoreApplication extends QuAccCoreApplication {

    public static DatabaseModuleMocks databaseModuleMocks;

    @Override
    protected List<Object> getModules() {
        databaseModuleMocks = new DatabaseModuleMocks();
        return Arrays.asList(
                new AndroidModule(this),
                databaseModuleMocks
        );
    }
}
