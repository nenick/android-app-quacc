package de.nenick.quacc.view.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EFragment;

import de.nenick.quacc.R;
import de.nenick.quacc.activities.AccountBookingEntriesActivity_;
import de.nenick.quacc.view.accounting_overview.AccountingListActivity_;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccountBookingEntriesActivity_.intent(this).start();
        getActivity().finish();
    }
}
