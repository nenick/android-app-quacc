package de.nenick.quacc.view.mvp;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;

/**
 * Base class for our fragments.
 * <p/>
 * Provides alternative lifecycle methods. They don't need a super call and give proper unit testing
 * support by not directly calling the super functionality.
 * <ul>
 * <li><b>onViewStart:</b> At this time all beans and views are injected but view is not visible.</li>
 * <li><b>onViewResumed:</b> View is full restored including last view states and the user can interact with the view.</li>
 * <li><b>onViewPaused:</b> The user can't interact with the view.</li>
 * <li><b>onViewFinished:</b> The view gets destroyed so release all resources at this time.</li>
 * </ul>
 */
@EFragment
public abstract class BasePresenterFragment extends Fragment {

    protected abstract BaseView getBaseView();

    @AfterViews
    protected void onAfterViews() {
        ((OnViewChangedListener) getBaseView()).onViewChanged((HasViews) this);
        onViewStart();
    }

    protected void onViewStart() {
    }

    @Override
    public void onResume() {
        super.onResume();
        onViewResume();
    }

    protected void onViewResume() {
    }

    @Override
    public void onPause() {
        super.onPause();
        onViewPause();
    }

    protected void onViewPause() {
    }

    @Override
    public void onDestroy() {
        super.onPause();
        onViewFinish();
    }

    protected void onViewFinish() {
    }
}
