package de.nenick.quacc.accounts;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.common.mvp.BaseView;

@EBean
public class AccountsView extends BaseView {

    @ViewById(R.id.account)
    Spinner accountSpinner;

    @ViewById(R.id.value)
    EditText initialValue;

    public void showAccounts(CharSequence[] stringArray) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);
    }

    public String getAccount() {
        return accountSpinner.getSelectedItem().toString();
    }

    public String getInitialValue() {
        return initialValue.getText().toString();
    }

    public void setInitialValue(String value) {
        initialValue.setText(value);
    }
}
