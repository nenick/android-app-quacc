package de.nenick.quacc.robolectric;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import de.nenick.robolectricpages.RoboBasePage;

public class RoboSupPage<A extends FragmentActivity, F extends Fragment> extends RoboBasePage<A, RoboSup<A, F>> {

    private String fragmentTag;

    public RoboSupPage(RoboSup<A, F> robo, String fragmentTag) {
        super(robo);
        this.fragmentTag = fragmentTag;
    }

    @Override
    public void startPage() {
        super.startPage();
        findFragment();
    }

    @Override
    protected void createPage() {
        super.createPage();
        findFragment();
    }

    @SuppressWarnings("unchecked")
    protected void findFragment() {
        robo.fragment = (F) robo.activity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
    }
}
