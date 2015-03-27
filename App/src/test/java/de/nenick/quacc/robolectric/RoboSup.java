package de.nenick.quacc.robolectric;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class RoboSup<A extends FragmentActivity, F extends Fragment> extends de.nenick.robolectricpages.Robo<A> {

    public F fragment;

}
