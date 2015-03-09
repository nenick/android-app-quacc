package de.nenick.robolectricpages.dialogs;

import android.app.DatePickerDialog;
import android.content.DialogInterface;

import org.robolectric.shadows.ShadowAlertDialog;

public class RoboDatePickerDialog extends RoboBaseDialog {

    public boolean isShowing() {
        return isShowing(DatePickerDialog.class);
    }

    public void pickDate(int day, int month, int year) {
        ((DatePickerDialog) ShadowAlertDialog.getLatestAlertDialog()).updateDate(year, month - 1, day);
    }

    public void clickOk() {
        clickDialogButton(DialogInterface.BUTTON_POSITIVE);
    }

    public void clickCancel() {
        clickDialogButton(DialogInterface.BUTTON_NEGATIVE);
    }
}
