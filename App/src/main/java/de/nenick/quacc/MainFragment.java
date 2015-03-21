package de.nenick.quacc;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EFragment;

import de.nenick.quacc.accountinglist.AccountingListActivity_;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccountingListActivity_.intent(this).start();
    }
}
