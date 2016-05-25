package de.nenick.quacc.test.usecases;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import de.nenick.espressomacchiato.elements.EspDevice;
import de.nenick.espressomacchiato.tools.EspPermissionsTool;
import de.nenick.quacc.settings.QuAccPreferences_;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.ReferencePicuture;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;
import de.nenick.quacc.test.pages.QuAccStartPage;

import static junit.framework.Assert.assertEquals;

public class StartupSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    QuAccStartPage startPage = new QuAccStartPage();
    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();
    EspDevice device = EspDevice.root();

    @Test
    public void testInitialOpenAndCloseApplication() {
        EspPermissionsTool.resetAllPermission();
        QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).initialAskedForSpeechRecognition(false);
        launcherPage.clickStartApp();

        // allow speech recognition
        startPage.speechRecognitionDialog().confirmButton().click();
        startPage.permissionDialog().allow();

        // should start with closed drawer
        bookingEntriesPage.assertIsVisible();
        bookingEntriesPage.drawer().assertIsHidden();

        // has no booking entries
        bookingEntriesPage.list().assertItemCountIs(0);

        ReferencePicuture.take("initial startup");

        // should close app with back press
        device.clickBackButton();
        launcherPage.assertIsVisible();

        assertEquals(true, QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).activateSpeechRecognition());
    }
}
