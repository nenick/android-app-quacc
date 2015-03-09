package de.nenick.quacc;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import de.nenick.quacc.addaccounting.AddAccountActivity_;
import de.nenick.quacc.addaccounting.AddAccountingDialog;
import de.nenick.quacc.addaccounting.AddAccountingDialog_;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    public static MainFragment build() {
        return MainFragment_.builder().build();
    }

    @AfterViews
    public void blub( )  {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.accounting_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner)getActivity().findViewById(R.id.spinner)).setAdapter(adapter);
    }

    @Click(R.id.button)
    protected void onStartAddAccountProcess() {
        AddAccountingDialog build = AddAccountingDialog_.builder().build();
        build.show(getActivity().getSupportFragmentManager(), "Add Accounting");
        //AddAccountActivity_.intent(getActivity()).start();
    }
}
