package de.nenick.quacc.account;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemSelect;

import de.nenick.quacc.R;
import de.nenick.quacc.core.account.GetAccountsFunction;
import de.nenick.quacc.valueparser.ParseValueFromStringFunction;
import de.nenick.quacc.valueparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.mvp.BasePresenterFragment;
import de.nenick.quacc.mvp.BaseView;
import de.nenick.quacc.database.account.AccountDb;
import de.nenick.quacc.database.provider.account.AccountCursor;

@EFragment(R.layout.fragment_accounts)
public class AccountsFragment extends BasePresenterFragment {

    @Bean
    AccountsView view;

    @Bean
    GetAccountsFunction getAccountsFunction;

    @Override
    protected BaseView getBaseView() {
        return view;
    }

    @Bean
    AccountDb accountDb;

    @Bean
    ParseValueFromStringFunction parseValueFromStringFunction;

    @Bean
    ParseValueFromIntegerFunction parseValueFromIntegerFunction;

    @Override
    protected void onViewStart() {
        view.showAccounts(getAccountsFunction.apply());
    }

    @Click(R.id.button)
    protected void onSave() {
        ParseValueFromStringFunction.Result apply = parseValueFromStringFunction.apply(view.getInitialValue());
        if(apply.report == ParseValueFromStringFunction.ParseResult.Successful) {
            accountDb.setInitialValue(view.getAccount(), apply.value);
        }
    }

    @ItemSelect(R.id.account)
    protected void onAccountSelection(boolean selected, int position) {
        AccountCursor accountByName = accountDb.getAccountByName(view.getAccount());
        accountByName.moveToFirst();
        view.setInitialValue(parseValueFromIntegerFunction.apply(accountByName.getInitialvalue()));
    }
}
