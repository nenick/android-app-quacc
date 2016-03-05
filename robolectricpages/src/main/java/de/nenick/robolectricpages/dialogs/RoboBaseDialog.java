package de.nenick.robolectricpages.dialogs;

import android.app.AlertDialog;

import org.robolectric.shadows.ShadowAlertDialog;

public class RoboBaseDialog {
    protected boolean isShowing(Class clazz) {
        AlertDialog latestAlertDialog = ShadowAlertDialog.getLatestAlertDialog();
        if (!(clazz.isInstance(latestAlertDialog))) {
            throw new IllegalStateException("Expected " + clazz.getSimpleName() + " but found " + latestAlertDialog.getClass().getSimpleName());
        }
        return latestAlertDialog.isShowing();
    }

    protected void clickDialogButton(int choice) {
        AlertDialog latestAlertDialog = ShadowAlertDialog.getLatestAlertDialog();
        if (!latestAlertDialog.getButton(choice).performClick()) {
            throw new IllegalStateException("Button has no click listener.");
        }
    }
}
