package de.nenick.quacc.datepicker;


import android.content.Context;
import android.test.mock.MockApplication;

import net.danlew.android.joda.JodaTimeAndroid;
import net.danlew.android.joda.ResourceZoneInfoProvider;

import org.joda.time.DateTimeZone;
import org.joda.time.JodaTimePermission;
import org.joda.time.tz.UTCProvider;
import org.joda.time.tz.ZoneInfoProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DatePickerDialogWrapperTest {

    @Mock
    DatePickerDialogWrapper.Callback callback;

    @InjectMocks
    DatePickerDialogWrapper datePickerDialogWrapper;

    int datePickerValueForMai = 4;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DateTimeZone.setProvider(new UTCProvider());
    }

    @Test
    public void shouldParsePickedDate() {
        datePickerDialogWrapper.onDateSet(null, 2015, datePickerValueForMai, 31);
        verify(callback).onDatePick("31.05.2015");
    }

}