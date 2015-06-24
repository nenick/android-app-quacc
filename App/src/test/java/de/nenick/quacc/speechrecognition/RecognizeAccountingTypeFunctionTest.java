package de.nenick.quacc.speechrecognition;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import de.nenick.quacc.core.accounting.type.AccountingType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;

public class RecognizeAccountingTypeFunctionTest {

    private final String einnahme = "Einnahme";
    @Spy
    RecognizeAccountingTypeFunction recognizeAccountingTypeFunction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApply() throws Exception {
        doReturn(einnahme).when(recognizeAccountingTypeFunction).getHumanReadableString(anyInt());
        SpeechResult apply = recognizeAccountingTypeFunction.apply(einnahme);
        assertThat(apply.value).isEqualTo(AccountingType.incoming.name());
    }
}