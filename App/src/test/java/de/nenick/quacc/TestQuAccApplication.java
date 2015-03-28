package de.nenick.quacc;

import java.util.Arrays;
import java.util.List;

import de.nenick.quacc.dagger.AndroidModule;
import de.nenick.quacc.dagger.CoreModuleMocks;

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
