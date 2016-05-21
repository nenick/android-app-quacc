package de.nenick.quacc.settings;

import de.devland.esperandro.annotations.Default;
import de.devland.esperandro.annotations.SharedPreferences;

@SharedPreferences
interface QuAccPreferencesDefinition {

    @Default(ofBoolean = true)
    boolean isFirstAppStart();
    void isFirstAppStart(boolean value);
}
