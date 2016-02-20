package de.nenick.quacc.core.accounting.interval.functions;

import java.util.Date;

import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;
import de.nenick.quacc.database.provider.interval.IntervalCursor;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class MockIntervalCursor {

    IntervalCursor cursor = mock(IntervalCursor.class);

    public static MockIntervalCursor create() {
        return new MockIntervalCursor();
    }

    public IntervalCursor get() {
        return cursor;
    }

    public MockIntervalCursor withId(long id) {
        given(cursor.getId()).willReturn(id);
        return this;
    }

    public MockIntervalCursor withInterval(BookingIntervalOption interval) {
        given(cursor.getInterval()).willReturn(interval.name());
        return this;
    }

    public MockIntervalCursor withUpdatedUntil(Date date) {
        given(cursor.getDateUpdatedUntil()).willReturn(date);
        return this;
    }

    public MockIntervalCursor withDateStart(Date date) {
        given(cursor.getDateStart()).willReturn(date);
        return this;
    }

    public MockIntervalCursor withDateLast(Date date) {
        given(cursor.getDateLast()).willReturn(date);
        return this;
    }
}
