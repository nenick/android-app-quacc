package de.nenick.quacc.apptest.espresso;

import android.test.ActivityInstrumentationTestCase2;
import android.view.WindowManager;

import de.nenick.quacc.apptest.DummyLauncherActivity_;
import de.nenick.quacc.apptest.pages.EspressoDummyLauncherPage;

public class BaseEspressoSpec extends ActivityInstrumentationTestCase2<DummyLauncherActivity_> {
    EspressoDummyLauncherPage launcherPage = new EspressoDummyLauncherPage();

    public BaseEspressoSpec() {
        super(DummyLauncherActivity_.class);
    }

    public void startApp() {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        launcherPage.clickStartApp();
    }

    @Override
    protected void tearDown() throws Exception {
        CloseAllActivitiesFunction.apply(getInstrumentation());
        super.tearDown();
    }
}
