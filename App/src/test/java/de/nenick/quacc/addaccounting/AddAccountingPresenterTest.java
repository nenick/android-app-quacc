package de.nenick.quacc.addaccounting;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.robolectric.RoboAppTest;

public class AddAccountingPresenterTest extends RoboAppTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RoboAddAccountingUcDefaultResults.apply();
    }


}
