package de.nenick.quacc.test.requirements.bookingentriesactivity;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import de.nenick.espressomacchiato.testbase.EspressoTestBase;
import de.nenick.espressomacchiato.tools.EspScreenshotTool;
import de.nenick.quacc.test.DummyLauncherActivity_;

public class EmulatorTest extends EspressoTestBase<DummyLauncherActivity_> {
    @Override
    public DummyLauncherActivity_ getActivity() {
        return null;
    }

    @Rule
    public ActivityTestRule<DummyLauncherActivity_> activityTestRule = new ActivityTestRule<>(DummyLauncherActivity_.class, false, false);

    @Test
    public void screenshot() {
        EspScreenshotTool.takeWithName("emulator issue");
    }
}
