package de.nenick.quacc.accounts;

import org.junit.Test;

import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.AccountDb_;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;
import de.nenick.robolectric.RoboComponentTestBase;
import de.nenick.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountsEditSpec extends RoboComponentTestBase {

    RoboSup<AccountsActivity_, AccountsFragment_> robo = new RoboSup<>();
    RoboAccountsPage accountsPage = new RoboAccountsPage(robo);

    @Test
    public void shouldSetInitialValue() {
        accountsPage.startPage();
        accountsPage.accounts().entry("Bar").select();
        accountsPage.initialValue().setText("500,23");
        accountsPage.save().click();

        AccountCursor bar = new AccountSelection().name("Bar").query(context.getContentResolver());
        bar.moveToFirst();
        assertThat(bar.getInitialvalue()).isEqualTo(50023);
    }

    @Test
    public void shouldShowInitialValue() {
        AccountDb_.getInstance_(context).setInitialValue("Bar", 599);

        accountsPage.startPage();
        accountsPage.accounts().entry("Bar").select();
        assertThat(accountsPage.initialValue().getText()).isEqualTo("5,99");
    }
}
