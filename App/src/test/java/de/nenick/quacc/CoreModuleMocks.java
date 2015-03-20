package de.nenick.quacc;

import android.content.Context;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.addaccounting.AddAccountingPresenter_;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc_;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc_;
import de.nenick.quacc.core.accounting.GetAccountingListUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc_;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.core.accounting.GetAccountsUc_;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;
import de.nenick.quacc.daggersupport.CoreModuleBase;
import de.nenick.quacc.daggersupport.ForApplication;

import static org.mockito.Mockito.mock;

@Module(
        injects = {AddAccountingPresenter_.class},
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

    public CoreModuleMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Provides
    public RecognizeAccountingTypeUc provideRecognizeAccountingTypeUc(@ForApplication Context context) {
        return recognizeAccountingTypeUc;
    }

    @Provides
    public GetAccountsUc provideGetAccountsUc(@ForApplication Context context) {
        return getAccountsUc;
    }

    @Provides
    public GetAccountingTypesUc provideGetAccountingTypesUc(@ForApplication Context context) {
        return getAccountingTypesUc;
    }

    @Provides
    public GetAccountingIntervalsUc provideGetAccountingIntervalsUc(@ForApplication Context context) {
        return getAccountingIntervalsUc;
    }

    @Provides
    public GetAccountingCategoriesUc provideGetAccountingCategoriesUc(@ForApplication Context context) {
        return getAccountingCategoriesUc;
    }
}
