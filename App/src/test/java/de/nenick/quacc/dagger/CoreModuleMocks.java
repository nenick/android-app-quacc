package de.nenick.quacc.dagger;

import android.content.Context;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.accountinglist.AccountingListPresenter_;
import de.nenick.quacc.addaccounting.AddAccountingPresenter_;
import de.nenick.quacc.core.accounting.AddNewAccountingUc;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingListUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;
import de.nenick.quacc.dagger.CoreModuleBase;
import de.nenick.quacc.dagger.ForApplication;

import static org.mockito.Mockito.mock;

@Module(
        injects = {AddAccountingPresenter_.class, AccountingListPresenter_.class},
        complete = false
)
public class CoreModuleMocks implements CoreModuleBase {

    @Mock
    public RecognizeAccountingTypeUc recognizeAccountingTypeUc;
    @Mock
    public GetAccountingCategoriesUc getAccountingCategoriesUc;
    @Mock
    public GetAccountingIntervalsUc getAccountingIntervalsUc;
    @Mock
    public GetAccountingTypesUc getAccountingTypesUc;
    @Mock
    public GetAccountsUc getAccountsUc;
    @Mock
    public GetAccountingListUc getAccountingListUc;
    @Mock
    public AddNewAccountingUc addNewAccountingUc;

    public CoreModuleMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Provides
    @Override
    public RecognizeAccountingTypeUc provideRecognizeAccountingTypeUc(@ForApplication Context context) {
        return recognizeAccountingTypeUc;
    }

    @Provides
    @Override
    public GetAccountsUc provideGetAccountsUc(@ForApplication Context context) {
        return getAccountsUc;
    }

    @Provides
    @Override
    public GetAccountingTypesUc provideGetAccountingTypesUc(@ForApplication Context context) {
        return getAccountingTypesUc;
    }

    @Provides
    @Override
    public GetAccountingIntervalsUc provideGetAccountingIntervalsUc(@ForApplication Context context) {
        return getAccountingIntervalsUc;
    }

    @Provides
    @Override
    public GetAccountingCategoriesUc provideGetAccountingCategoriesUc(@ForApplication Context context) {
        return getAccountingCategoriesUc;
    }

    @Provides
    @Override
    public GetAccountingListUc provideGetAccountingListUc(@ForApplication Context context) {
        return getAccountingListUc;
    }

    @Override
    @Provides
    public AddNewAccountingUc provideAddNewAccountingUc(@ForApplication Context context) {
        return addNewAccountingUc;
    }
}
