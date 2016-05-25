package de.nenick.quacc.test.pages;

import android.Manifest;

import de.nenick.espressomacchiato.elements.EspPage;
import de.nenick.espressomacchiato.elements.EspPermissionDialog;
import de.nenick.espressomacchiato.elements.support.EspSupportAlertDialog;

public class QuAccStartPage extends EspPage {

    public QuAccStartPage() {
        super(0);
    }

    public EspSupportAlertDialog speechRecognitionDialog() {
        return EspSupportAlertDialog.build();
    }

    public EspPermissionDialog permissionDialog() {
        return EspPermissionDialog.build(Manifest.permission.RECORD_AUDIO);
    }
}
