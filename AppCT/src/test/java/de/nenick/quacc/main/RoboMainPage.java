package de.nenick.quacc.main;

import de.nenick.quacc.main.MainActivity;
import de.nenick.quacc.main.MainActivity_;
import de.nenick.quacc.main.MainFragment_;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RoboSupPage;

public class RoboMainPage extends RoboSupPage<MainActivity_, MainFragment_> {

    public RoboMainPage(RoboSup<MainActivity_, MainFragment_> robo) {
        super(robo, MainActivity.TAG_FRAGMENT);
    }
}
