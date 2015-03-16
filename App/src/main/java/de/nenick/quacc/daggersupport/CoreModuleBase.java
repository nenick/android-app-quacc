package de.nenick.quacc.daggersupport;


import android.content.Context;

import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;


public interface CoreModuleBase {

    RecognizeAccountingTypeUc provideRecognizeAccountingTypeUc(@ForApplication Context context);

    GetAccountsUc provideGetAccountsUc(@ForApplication Context context);

    GetAccountingIntervalsUc provideGetAccountingIntervalsUc(@ForApplication Context context);

    GetAccountingTypesUc provideGetAccountingTypesUc(@ForApplication Context context);

    GetAccountingCategoriesUc provideGetAccountingCategoriesUc(@ForApplication Context context);
}
