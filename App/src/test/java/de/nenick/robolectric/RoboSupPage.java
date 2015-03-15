package de.nenick.robolectric;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import de.nenick.robolectricpages.RoboBasePage;

public class RoboSupPage<A extends FragmentActivity, F extends Fragment> extends RoboBasePage<A, RoboSupTest<A, F>> {

    private String fragmentTag;

    public RoboSupPage(Class<A> clazz, RoboSupTest<A, F> roboSupTest, String fragmentTag) {
        super(clazz, roboSupTest);
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
