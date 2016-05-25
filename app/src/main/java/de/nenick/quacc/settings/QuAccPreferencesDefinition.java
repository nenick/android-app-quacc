package de.nenick.quacc.settings;

import de.devland.esperandro.annotations.Default;
import de.devland.esperandro.annotations.SharedPreferences;

@SharedPreferences
interface QuAccPreferencesDefinition {

    @Default(ofBoolean = false)
    boolean initialDatabaseContentStored();
    void initialDatabaseContentStored(boolean value);

    @Default(ofBoolean = false)
    boolean initialAskedForSpeechRecognition();
    void initialAskedForSpeechRecognition(boolean value);

    @Default(ofBoolean = false)
    boolean activateSpeechRecognition();
    void activateSpeechRecognition(boolean value);
}
