package de.nenick.quacc.activities.start;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class SpeechRecognitionDialogFragment extends DialogFragment {

    private StartActivity activity;

    public void setCallback(StartActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle("Sprachunterstützung")
                .setMessage("Diese App unterstützt Sprachkontrolle, wollen Sie diese jetzt aktivieren?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.onActivateSpeechRecognitionRequested();
                    }
                })
                .setNegativeButton("Später", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.onDeactivateSpeechRecognitionRequested();
                    }
                }).create();
    }
}
