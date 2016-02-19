package de.nenick.quacc.database.account;

import de.nenick.quacc.database.provider.account.AccountSelection;

public class AccountSpecAll extends AccountSpec {

    @Override
    public AccountSelection toSelection() {
        return new AccountSelection();
    }
}
