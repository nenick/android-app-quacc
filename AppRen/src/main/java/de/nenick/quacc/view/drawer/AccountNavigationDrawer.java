package de.nenick.quacc.view.drawer;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;

import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecAll;
import de.nenick.quacc.database.provider.account.AccountCursor;

@EViewGroup
public class AccountNavigationDrawer extends NavigationView implements NavigationView.OnNavigationItemSelectedListener {

    public interface AccountSelectionListener {
        void onAccountSelection(long accountId);
    }

    private AccountSelectionListener accountSelectionListener;
    private static final int GROUP_ACCOUNTS = 1;

    public AccountNavigationDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAccountSelectionListener(AccountSelectionListener listener) {
        accountSelectionListener = listener;
    }

    @AfterViews
    void onAfterViewsCreated() {
        setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if(item.getGroupId() == GROUP_ACCOUNTS) {
            accountSelectionListener.onAccountSelection(item.getItemId());
            return true;
        }
        return false;
    }

    public void bindAccounts(AccountCursor accountCursor) {
        Menu menu = getMenu();
        SubMenu accountMenu = menu.addSubMenu(GROUP_ACCOUNTS, "Accounts".hashCode(), Menu.NONE, "Accounts");

        while (accountCursor.moveToNext()) {
            MenuItem menuItem = accountMenu.add(GROUP_ACCOUNTS, (int) accountCursor.getId(), Menu.NONE, null);
            ListItemAccount accountEntry = ListItemAccount.create(getContext());
            MenuItemCompat.setActionView(menuItem, accountEntry);
            accountEntry.bind(accountCursor);
        }

        accountMenu.setGroupCheckable(GROUP_ACCOUNTS, true, true);
    }

    public void selectAccount(long accountId) {
        setCheckedItem((int) accountId);
    }
}
