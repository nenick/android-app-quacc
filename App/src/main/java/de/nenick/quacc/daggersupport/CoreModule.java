package de.nenick.quacc.daggersupport;

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
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc_;

@Module(
        injects = {AddAccountingPresenter_.class},
        complete = false
)
public class CoreModule implements CoreModuleBase {

    @Provides
    public RecognizeAccountingTypeUc provideRecognizeAccountingTypeUc(@ForApplication Context context) {
        return RecognizeAccountingTypeUc_.getInstance_(context);
    }

    @Provides
    public GetAccountsUc provideGetAccountsUc(@ForApplication Context context) {
        return GetAccountsUc_.getInstance_(context);
    }

    @Provides
    public GetAccountingTypesUc provideGetAccountingTypesUc(@ForApplication Context context) {
        return GetAccountingTypesUc_.getInstance_(context);
    }

    @Provides
    public GetAccountingIntervalsUc provideGetAccountingIntervalsUc(@ForApplication Context context) {
        return GetAccountingIntervalsUc_.getInstance_(context);
    }

    @Provides
    public GetAccountingCategoriesUc provideGetAccountingCategoriesUc(@ForApplication Context context) {
        return GetAccountingCategoriesUc_.getInstance_(context);
    }
}
