package de.nenick.quacc.accounting.interval.functions;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.tz.UTCProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import de.nenick.quacc.database.IntervalDb;
import de.nenick.quacc.database.provider.interval.IntervalCursor;

import static de.nenick.quacc.database.AccountingInterval.daily;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class UpdateIntervalFunctionTest {

    public static final long intervalId = 42l;
    @Mock
    IntervalDb intervalDb;

    @Mock
    CreateIntervalAccountingFunction createIntervalAccountingFunction;

    @InjectMocks
    UpdateIntervalFunction updateIntervalFunction;

    @Before
    public void setup() {
        DateTimeZone.setProvider(new UTCProvider());
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateAccountingForRange_daily() {
        Date intervalStartDate = new Date(2014, 12, 30);
        Date updateUntil = new Date(2015, 1, 2);

        IntervalCursor cursor = MockIntervalCursor.create()
                .withDateStart(intervalStartDate)
                .withInterval(daily)
                .withUpdatedUntil(IntervalDb.NO_DATE_GIVEN)
                .get();

        updateIntervalFunction.apply(cursor, new DateTime(updateUntil));

        verify(createIntervalAccountingFunction).apply(cursor, new Date(2014, 12, 30));
        verify(createIntervalAccountingFunction).apply(cursor, new Date(2014, 12, 31));
        verify(createIntervalAccountingFunction).apply(cursor, new Date(2015, 1, 1));
        verify(createIntervalAccountingFunction).apply(cursor, new Date(2015, 1, 2));
        verifyNoMoreInteractions(createIntervalAccountingFunction);
    }

    @Test
    public void shouldUpdateIntervalState_dateUpdateUntil() {
        Date updatedUntil = new Date(2015, 1, 2);

        IntervalCursor cursor = MockIntervalCursor.create()
                .withId(intervalId)
                .withInterval(daily)
                .withUpdatedUntil(updatedUntil)
                .get();

        DateTime updateUntil = new DateTime(new Date(2015, 1, 2));
        updateIntervalFunction.apply(cursor, updateUntil);

        verify(intervalDb).updatedUntil(intervalId, updateUntil.toDate());
    }

}