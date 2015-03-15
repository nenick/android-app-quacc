package de.nenick.robolectric;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import de.nenick.robolectricpages.RoboBaseTest;

public class RoboSupTest<A extends FragmentActivity, F extends Fragment> extends RoboBaseTest<A> {

    public F fragment;

}
