package de.nenick.robolectric;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;

public class RoboSup<A extends FragmentActivity, F extends Fragment> extends de.nenick.robolectricpages.Robo<A> {

    public F fragment;

}
