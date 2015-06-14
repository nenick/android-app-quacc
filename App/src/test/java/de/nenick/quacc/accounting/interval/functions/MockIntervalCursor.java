package de.nenick.quacc.accounting.interval.functions;

import java.util.Date;

import de.nenick.quacc.database.AccountingInterval;
import de.nenick.quacc.database.provider.interval.IntervalCursor;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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

    public MockIntervalCursor withInterval(AccountingInterval interval) {
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
}
