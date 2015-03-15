package de.nenick.quacc;

import java.util.Arrays;
import java.util.List;

import de.nenick.quacc.daggersupport.AndroidModule;

public class TestQuAccApplication extends QuAccApplication {

    public static CoreModuleMocks coreModuleMocks;

    @Override
    protected List<Object> getModules() {
        coreModuleMocks = new CoreModuleMocks();
        return Arrays.asList(
                new AndroidModule(this),
                coreModuleMocks
        );
    }
}
