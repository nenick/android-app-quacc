package de.nenick.quacc.database.account;

import de.nenick.quacc.database.provider.account.AccountSelection;

public class AccountSpecByName extends AccountSpec {

    private String name;

    public AccountSpecByName(String name) {
        this.name = name;
    }

    @Override
    public AccountSelection toSelection() {
        AccountSelection selection = new AccountSelection();
        selection.name(name);
        return selection;
    }
}
