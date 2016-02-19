package de.nenick.quacc.database.account;

import de.nenick.quacc.database.provider.account.AccountSelection;

public class AccountSpecById extends AccountSpec {

    private long id;

    public AccountSpecById(long id) {
        this.id = id;
    }

    @Override
    public AccountSelection toSelection() {
        AccountSelection selection = new AccountSelection();
        selection.id(id);
        return selection;
    }
}
