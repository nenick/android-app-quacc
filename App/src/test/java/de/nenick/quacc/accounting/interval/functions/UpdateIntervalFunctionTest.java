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

import de.nenick.quacc.accounting.creation.CreateIntervalAccountingFunction;
import de.nenick.quacc.accounting.creation.UpdateIntervalFunction;
import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.interval.IntervalDb;
import de.nenick.quacc.database.provider.interval.IntervalCursor;

import static de.nenick.quacc.accounting.interval.AccountingInterval.daily;
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
        DateTime intervalStartDate = QuAccDateUtil.toDateTime(30, 12, 2014);
        DateTime updateUntil = QuAccDateUtil.toDateTime(2, 1, 2015);

        IntervalCursor cursor = MockIntervalCursor.create()
                .withDateStart(intervalStartDate.toDate())
                .withInterval(daily)
                .withUpdatedUntil(IntervalDb.NO_DATE_GIVEN)
                .withDateLast(IntervalDb.NO_DATE_GIVEN)
                .get();

        updateIntervalFunction.apply(cursor, new DateTime(updateUntil));

        verify(createIntervalAccountingFunction).apply(cursor, QuAccDateUtil.toDateTime(30, 12, 2014).toDate());
        verify(createIntervalAccountingFunction).apply(cursor, QuAccDateUtil.toDateTime(31, 12, 2014).toDate());
        verify(createIntervalAccountingFunction).apply(cursor, QuAccDateUtil.toDateTime(1, 1, 2015).toDate());
        verify(createIntervalAccountingFunction).apply(cursor, QuAccDateUtil.toDateTime(2, 1, 2015).toDate());
        verifyNoMoreInteractions(createIntervalAccountingFunction);
    }

    @Test
    public void shouldUpdateIntervalState_dateUpdateUntil() {
        DateTime updateUntil = QuAccDateUtil.toDateTime(1, 2, 2015);
        DateTime updatedUntil = updateUntil.minusDays(1);
        IntervalCursor cursor = MockIntervalCursor.create()
                .withId(intervalId)
                .withInterval(daily)
                .withUpdatedUntil(updatedUntil.toDate())
                .withDateLast(updatedUntil.toDate())
                .get();
        updateIntervalFunction.apply(cursor, updateUntil);
        verify(intervalDb).updatedUntil(intervalId, updateUntil.toDate(), updateUntil.toDate());
    }

}