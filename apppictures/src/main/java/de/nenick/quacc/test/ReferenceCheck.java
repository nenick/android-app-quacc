package de.nenick.quacc.test;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.nenick.espressomacchiato.tools.EspScreenshotTool;

import static org.junit.Assert.assertEquals;

public class ReferenceCheck {

    public static final String screenshotUrl = "https://circle-artifacts.com/gh/nenick/QuAcc/259/artifacts/0/tmp/circle-junit.joMmG5P/test-screenshots/";

    public static void download(String name) {
        try {

            InputStream in = new java.net.URL(screenshotUrl + name).openStream();

            OutputStream output = new FileOutputStream(downloadLocation(name));

            byte data[] = new byte[1024];

            int count;
            while ((count = in.read(data)) != -1) {
                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    private static String downloadLocation(String name) {
        return InstrumentationRegistry.getTargetContext().getFilesDir().toString() + "/" + name;
    }

    public static void check(String pictureName) {
        download(pictureName);
        String picturePath = EspScreenshotTool.screenshotLocation(pictureName.replace(".png", "")).getAbsolutePath();
        if (!new File(picturePath).exists()) throw new IllegalStateException(pictureName);
        double percentage = EspScreenshotTool.comparePercentage(
                downloadLocation(pictureName),
                picturePath);

        assertEquals(100.0, percentage, EspScreenshotTool.COMPARE_DELTA_TIME_CHANGE);
    }
}

