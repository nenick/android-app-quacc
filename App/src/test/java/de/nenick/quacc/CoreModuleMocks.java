package de.nenick.quacc;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.addaccounting.AddAccountingPresenter_;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;
import de.nenick.quacc.daggersupport.ForApplication;

import static org.mockito.Mockito.mock;

@Module(
        injects = {AddAccountingPresenter_.class},
        complete = false
)
public class CoreModuleMocks {

    public RecognizeAccountingTypeUc recognizeAccountingTypeUc;

    @Provides
    public RecognizeAccountingTypeUc provideRecognizeAccountingTypeUc(@ForApplication Context context) {
        return recognizeAccountingTypeUc = mock(RecognizeAccountingTypeUc.class);
    }
}
