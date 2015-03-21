package de.nenick.quacc;

import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RoboSupPage;
import de.nenick.robolectricpages.components.RoboListView;

public class RoboMainPage extends RoboSupPage<MainActivity_, MainFragment_> {

    public RoboMainPage(RoboSup<MainActivity_, MainFragment_> robo) {
        super(robo, MainActivity.TAG_FRAGMENT);
    }
}
