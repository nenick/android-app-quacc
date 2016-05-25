package de.nenick.quacc.test.requirements.startactivity;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import de.nenick.espressomacchiato.elements.EspDevice;
import de.nenick.espressomacchiato.tools.EspPermissionsTool;
import de.nenick.quacc.settings.QuAccPreferences_;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;
import de.nenick.quacc.test.pages.QuAccStartPage;

import static junit.framework.Assert.assertEquals;

public class SpeechRecognitionSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    QuAccStartPage startPage = new QuAccStartPage();
    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();
    EspDevice device = EspDevice.root();

    // test allow speech recognition already tested inStartupSpec

    @Test
    public void testDenySpeechRecognition() {
        QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).initialAskedForSpeechRecognition(false);
        launcherPage.clickStartApp();

        startPage.speechRecognitionDialog().denyButton().click();
        bookingEntriesPage.assertIsVisible();
        assertEquals(false, QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).activateSpeechRecognition());
    }

    @Test
    public void testSpeechRecognitionRequestedOnlyOnce() {
        QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).initialAskedForSpeechRecognition(false);
        launcherPage.clickStartApp();

        startPage.speechRecognitionDialog().denyButton().click();
        bookingEntriesPage.assertIsVisible();
        assertEquals(false, QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).activateSpeechRecognition());

        device.clickBackButton();
        launcherPage.clickStartApp();
        bookingEntriesPage.assertIsVisible();

    }

    @Test
    public void testDenyPermission() {
        EspPermissionsTool.resetAllPermission();
        QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).initialAskedForSpeechRecognition(false);
        launcherPage.clickStartApp();

        startPage.speechRecognitionDialog().confirmButton().click();
        startPage.permissionDialog().deny();
        bookingEntriesPage.assertIsVisible();
        assertEquals(false, QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).activateSpeechRecognition());
    }

    @Test
    public void testSpeechRecognitionAfterRotation() {
        QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).initialAskedForSpeechRecognition(false);
        launcherPage.clickStartApp();

        device.rotateToLandscape();
        startPage.speechRecognitionDialog().denyButton().click();
        bookingEntriesPage.assertIsVisible();
        assertEquals(false, QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).activateSpeechRecognition());
    }
}
