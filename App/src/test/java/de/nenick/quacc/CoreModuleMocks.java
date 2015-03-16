package de.nenick.quacc;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.addaccounting.AddAccountingPresenter_;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc_;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc_;
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

    public RecognizeAccountingTypeUc recognizeAccountingTypeUc;
    public GetAccountingCategoriesUc getAccountingCategoriesUc;
    public GetAccountingIntervalsUc getAccountingIntervalsUc;
    public GetAccountingTypesUc getAccountingTypesUc;
    public GetAccountsUc getAccountsUc;

    @Provides
    public RecognizeAccountingTypeUc provideRecognizeAccountingTypeUc(@ForApplication Context context) {
        return recognizeAccountingTypeUc = mock(RecognizeAccountingTypeUc.class);
    }

    @Provides
    public GetAccountsUc provideGetAccountsUc(@ForApplication Context context) {
        return getAccountsUc = mock(GetAccountsUc.class);
    }

    @Provides
    public GetAccountingTypesUc provideGetAccountingTypesUc(@ForApplication Context context) {
        return getAccountingTypesUc = mock(GetAccountingTypesUc.class);
    }

    @Provides
    public GetAccountingIntervalsUc provideGetAccountingIntervalsUc(@ForApplication Context context) {
        return getAccountingIntervalsUc = mock(GetAccountingIntervalsUc.class);
    }

    @Provides
    public GetAccountingCategoriesUc provideGetAccountingCategoriesUc(@ForApplication Context context) {
        return getAccountingCategoriesUc = mock(GetAccountingCategoriesUc.class);
    }
}
