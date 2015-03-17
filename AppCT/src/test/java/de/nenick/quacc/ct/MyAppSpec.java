package de.nenick.quacc.ct;

import android.widget.Spinner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import de.nenick.quacc.R;
import de.nenick.quacc.addaccounting.AddAccountingActivity_;
import de.nenick.quacc.addaccounting.RoboAddAccountingPage;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidStudioAwareRobolectricTestRunner.class)
public class MyAppSpec {

    @Test
    public void blub() {
        // Need https://code.google.com/p/android/issues/detail?id=126013 to reuse my pages
         RoboAddAccountingPage page;
        AddAccountingActivity_ activity = Robolectric.buildActivity(AddAccountingActivity_.class).setup().get();
        Spinner accounts = (Spinner) activity.findViewById(R.id.account);
        assertThat(accounts.getAdapter().getCount()).isEqualTo(4);
    }
}
