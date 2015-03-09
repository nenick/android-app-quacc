package de.nenick.quacc;

import android.app.Activity;
import android.widget.TextView;

import org.junit.Test;
import org.robolectric.Robolectric;

import de.nenick.robolectric.UnitTesting;

import static org.assertj.core.api.Assertions.assertThat;

public class TryRoboTest extends UnitTesting {

    @Test
    public void doIt() {
        Activity activity =
                Robolectric.setupActivity(MainActivity.class);

        TextView results =
                (TextView) activity.findViewById(R.id.textView);
        String resultsText = results.getText().toString();

        // failing test gives much better feedback
        // to show that all works correctly ;)
        assertThat(resultsText).isEqualTo("Testing Android Rocks!");
    }
}
