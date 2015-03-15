package de.nenick.quacc.daggersupport;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.addaccounting.AddAccountingPresenter;
import de.nenick.quacc.addaccounting.AddAccountingPresenter_;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc_;

@Module(
        injects = {AddAccountingPresenter_.class},
        complete = false
)
public class CoreModule {

    @Provides
    public RecognizeAccountingTypeUc provideUserRepository(@ForApplication Context context) {
        return RecognizeAccountingTypeUc_.getInstance_(context);
    }
}
