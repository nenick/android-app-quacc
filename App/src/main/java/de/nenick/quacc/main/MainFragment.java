package de.nenick.quacc.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EFragment;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.list.AccountingListActivity_;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccountingListActivity_.intent(this).start();
        getActivity().finish();
    }
}
