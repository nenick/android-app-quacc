package de.nenick.quacc.view.account;

import android.content.Intent;

import org.robolectric.RuntimeEnvironment;

import de.nenick.quacc.R;
import de.nenick.quacc.robolectric.RoboSup;
import de.nenick.quacc.robolectric.RoboSupPage;
import de.nenick.robolectricpages.components.RoboButton;
import de.nenick.robolectricpages.components.RoboSpinner;
import de.nenick.robolectricpages.components.RoboTextView;

public class RoboAccountsPage extends RoboSupPage<AccountsActivity_, AccountsFragment_> {

    public RoboAccountsPage(RoboSup<AccountsActivity_, AccountsFragment_> robo) {
        super(robo, AccountsActivity.TAG_FRAGMENT);
    }

    public static Intent Intent() {
        return AccountsActivity_.intent(RuntimeEnvironment.application).get();
    }

    public RoboSpinner accounts() {
        return new RoboSpinner(robo, R.id.account);
    }

    public RoboTextView initialValue() {
        return new RoboTextView(robo, R.id.value);
    }

    public RoboButton save() {
        return new RoboButton(robo, R.id.button);
    }
}
