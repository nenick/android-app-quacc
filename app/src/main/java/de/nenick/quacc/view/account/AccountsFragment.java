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
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.tools.AmountParser;
import de.nenick.quacc.view.mvp.BasePresenterFragment;
import de.nenick.quacc.view.mvp.BaseView;
import de.nenick.toolscollection.amountparser.AmountFromStringResult;

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
    AmountParser amountParser;

    @Override
    protected void onViewStart() {
        view.showAccounts(getAccountsFunction.apply());
    }

    @Click(R.id.button)
    protected void onSave() {
        AmountFromStringResult apply = amountParser.asInteger(view.getInitialValue());
        if(apply.report == AmountFromStringResult.ParseResult.Successful) {
            AccountContentValues account = new AccountContentValues().putInitialvalue(apply.amount);
            AccountSpecByName byName = new AccountSpecByName(view.getAccount());
            accountRepository.update(account, byName);
        }
    }

    @ItemSelect(R.id.account)
    protected void onAccountSelection(boolean selected, int position) {
        AccountCursor accountByName = accountRepository.query(new AccountSpecByName(view.getAccount()));
        accountByName.moveToFirst();
        view.setInitialValue(amountParser.asString(accountByName.getInitialvalue()));
    }
}
