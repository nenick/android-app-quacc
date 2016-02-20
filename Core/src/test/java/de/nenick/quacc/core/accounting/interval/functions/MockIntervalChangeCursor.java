package de.nenick.quacc.core.accounting.interval.functions;

import org.mockito.BDDMockito;

import java.util.Date;

import de.nenick.quacc.database.provider.interval.IntervalCursor;
import de.nenick.quacc.database.provider.intervalchange.IntervalChangeCursor;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class MockIntervalChangeCursor {

    IntervalChangeCursor cursor = mock(IntervalChangeCursor.class);

    public static MockIntervalChangeCursor create() {
        return new MockIntervalChangeCursor();
    }

    public IntervalChangeCursor get() {
        return cursor;
    }

    public MockIntervalChangeCursor withMoveToNext(int result, int times) {
        BDDMockito.BDDMyOngoingStubbing<Boolean> given = given(cursor.moveToNext());
        for (int j = 0; j < times; j++) {
            for (int i = 0; i < result; i++) {
                given = given.willReturn(true);
            }
            given.willReturn(false);
        }
        return this;
    }

    public MockIntervalChangeCursor withId(long id) {
        given(cursor.getId()).willReturn(id);
        return this;
    }

    public MockIntervalChangeCursor withChange(IntervalChange ... change) {
        BDDMockito.BDDMyOngoingStubbing<String> given = given(cursor.getChange());
        for (IntervalChange intervalChange : change) {
            given = given.willReturn(intervalChange.name());
        }
        return this;
    }

    public MockIntervalChangeCursor withDate(Date ... date) {
        BDDMockito.BDDMyOngoingStubbing<Date> given = given(cursor.getDate());
        for (Date date1 : date) {
            given = given.willReturn(date1);
        }
        return this;
    }

    public MockIntervalChangeCursor withValue(int ... values) {
        BDDMockito.BDDMyOngoingStubbing<Integer> given = given(cursor.getValue());
        for (int value : values) {
            given = given.willReturn(value);
        }
        return this;
    }

    public MockIntervalChangeCursor withComment(String ... comments) {
        BDDMockito.BDDMyOngoingStubbing<String> given = given(cursor.getComment());
        for (String comment : comments) {
            given = given.willReturn(comment);
        }
        return this;
    }
}
