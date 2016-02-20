package de.nenick.quacc.view.account;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemSelect;

import de.nenick.quacc.R;
import de.nenick.quacc.core.account.GetAccountsFunction;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecByName;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.valueparser.ParseValueFromStringFunction;
import de.nenick.quacc.valueparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.view.mvp.BasePresenterFragment;
import de.nenick.quacc.view.mvp.BaseView;
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
    AccountRepository accountRepository;

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
            AccountContentValues account = new AccountContentValues().putInitialvalue(apply.value);
            AccountSpecByName byName = new AccountSpecByName(view.getAccount());
            accountRepository.update(account, byName);
        }
    }

    @ItemSelect(R.id.account)
    protected void onAccountSelection(boolean selected, int position) {
        AccountCursor accountByName = accountRepository.query(new AccountSpecByName(view.getAccount()));
        accountByName.moveToFirst();
        view.setInitialValue(parseValueFromIntegerFunction.apply(accountByName.getInitialvalue()));
    }
}
