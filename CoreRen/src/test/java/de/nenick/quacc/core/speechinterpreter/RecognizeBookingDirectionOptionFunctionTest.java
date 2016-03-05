package de.nenick.quacc.core.speechinterpreter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;

public class RecognizeBookingDirectionOptionFunctionTest {

    private final String incoming = "Einnahme";
    
    @Spy
    RecognizeAccountingTypeFunction recognizeAccountingTypeFunction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApply() throws Exception {
        doReturn(incoming).when(recognizeAccountingTypeFunction).getHumanReadableString(anyInt());
        SpeechResult apply = recognizeAccountingTypeFunction.apply(incoming);
        assertThat(apply.value).isEqualTo(BookingDirectionOption.incoming.name());
    }
}