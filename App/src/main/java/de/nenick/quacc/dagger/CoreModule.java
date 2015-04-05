package de.nenick.quacc.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.accountinglist.AccountingListPresenter_;
import de.nenick.quacc.addaccounting.AddAccountingPresenter_;
import de.nenick.quacc.core.accounting.AddNewAccountingUc;
import de.nenick.quacc.core.accounting.AddNewAccountingUc_;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc_;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc_;
import de.nenick.quacc.core.accounting.GetAccountingListUc;
import de.nenick.quacc.core.accounting.GetAccountingListUc_;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc_;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.core.accounting.GetAccountsUc_;
import de.nenick.quacc.core.accounting.ParseAccountingValueUc;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc_;

@Module(
        injects = {AddAccountingPresenter_.class, AccountingListPresenter_.class},
        complete = false
)
public class CoreModule implements CoreModuleBase {

    @Provides
    @Override
    public RecognizeAccountingTypeUc provideRecognizeAccountingTypeUc(@ForApplication Context context) {
        return RecognizeAccountingTypeUc_.getInstance_(context);
    }

    @Provides
    @Override
    public GetAccountsUc provideGetAccountsUc(@ForApplication Context context) {
        return GetAccountsUc_.getInstance_(context);
    }

    @Provides
    @Override
    public GetAccountingTypesUc provideGetAccountingTypesUc(@ForApplication Context context) {
        return GetAccountingTypesUc_.getInstance_(context);
    }

    @Provides
    @Override
    public GetAccountingIntervalsUc provideGetAccountingIntervalsUc(@ForApplication Context context) {
        return GetAccountingIntervalsUc_.getInstance_(context);
    }

    @Provides
    @Override
    public GetAccountingCategoriesUc provideGetAccountingCategoriesUc(@ForApplication Context context) {
        return GetAccountingCategoriesUc_.getInstance_(context);
    }

    @Provides
    @Override
    public GetAccountingListUc provideGetAccountingListUc(@ForApplication Context context) {
        return GetAccountingListUc_.getInstance_(context);
    }

    @Override
    @Provides
    public AddNewAccountingUc provideAddNewAccountingUc(@ForApplication Context context) {
        return AddNewAccountingUc_.getInstance_(context);
    }

    @Override
    @Provides
    public ParseAccountingValueUc provideParseAccountingValueUc() {
        return new ParseAccountingValueUc();
    }
}
