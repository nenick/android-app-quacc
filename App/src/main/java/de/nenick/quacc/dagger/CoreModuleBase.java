package de.nenick.quacc.dagger;


import android.content.Context;

import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingListUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;


public interface CoreModuleBase {

    RecognizeAccountingTypeUc provideRecognizeAccountingTypeUc(Context context);

    GetAccountsUc provideGetAccountsUc(Context context);

    GetAccountingIntervalsUc provideGetAccountingIntervalsUc(Context context);

    GetAccountingTypesUc provideGetAccountingTypesUc(Context context);

    GetAccountingCategoriesUc provideGetAccountingCategoriesUc(Context context);

    GetAccountingListUc provideGetAccountingListUc(Context context);
}
