package de.nenick.quacc.settings;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref
public interface QuAccPreferences {

    @DefaultBoolean(true)
    boolean isFirstAppStart();

}
