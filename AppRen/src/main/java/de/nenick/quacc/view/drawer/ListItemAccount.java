package de.nenick.quacc.view.drawer;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.database.provider.account.AccountCursor;

@EViewGroup(R.layout.item_account_summery)
public class ListItemAccount extends LinearLayout {

    @ViewById(R.id.account)
    TextView textViewAccount;

    @ViewById(R.id.amount)
    TextView textViewAmount;

    static ListItemAccount create(Context context) {
        return ListItemAccount_.build(context);
    }

    public ListItemAccount(Context context) {
        super(context);
    }

    public void bind(AccountCursor accountCursor) {
        textViewAccount.setText(accountCursor.getName());
        //accountCursor.getAmount();
    }
}
