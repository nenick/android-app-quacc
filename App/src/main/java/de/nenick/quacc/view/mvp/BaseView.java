package de.nenick.quacc.view.mvp;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public abstract class BaseView {

    @RootContext
    protected ActionBarActivity context;

    public void showToast(int resourceId) {
        Toast.makeText(context, resourceId, Toast.LENGTH_SHORT).show();
    }

    public void closeSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = context.getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public void finish() {
        context.finish();
    }
}
