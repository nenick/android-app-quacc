package de.nenick.quacc.activities.start;


import android.database.sqlite.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.initialdata.DatabaseInitialData;
import de.nenick.quacc.settings.QuAccPreferences;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StartActivityTest {

    @Mock
    DatabaseInitialData databaseInitialData;

    @Mock
    QuAccPreferences preferences;

    @InjectMocks
    StartActivity startActivity = new StartActivity();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInitialData() {
        when(preferences.initialDatabaseContentStored()).thenReturn(true);
        startActivity.onAfterInjectBeans();
        verify(databaseInitialData).insert(any(SQLiteDatabase.class));
        verify(preferences).initialDatabaseContentStored(false);
    }

    @Test
    public void testInitialDataSkipIfAlreadyDone() {
        when(preferences.initialDatabaseContentStored()).thenReturn(false);
        startActivity.onAfterInjectBeans();
        verify(databaseInitialData, never()).insert(any(SQLiteDatabase.class));
    }
}