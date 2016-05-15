package de.nenick.quacc.test;

import android.Manifest;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.nenick.espressomacchiato.testbase.EspressoTestBase;
import de.nenick.espressomacchiato.tools.EspPermissionsTool;
import de.nenick.espressomacchiato.tools.EspScreenshotTool;

import static org.junit.Assert.assertEquals;

public class ReferencePicuture {

    public static final String screenshotUrl = "https://circle-artifacts.com/gh/nenick/QuAcc/228/artifacts/0/tmp/circle-junit.nuYiRKs/test-screenshots/";

    private static final double COMPARE_DELTA_DATE_CHANGE = 0.4;

    public static void download(String name) {
        try {

            InputStream in = new java.net.URL(screenshotUrl + name + ".png").openStream();

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
        return InstrumentationRegistry.getTargetContext().getFilesDir().toString() + "/" + name + ".png";
    }

    public static void checkWithoutError(String pictureName) {
        check(pictureName, false);
    }

    public static void check(String pictureName) {
        check(pictureName, true);
    }

    private static void check(String pictureName, boolean throwError) {
        EspPermissionsTool.ensurePermissions(EspressoTestBase.currentActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        pictureName = "reference_" + pictureName.replace(" ", "_");
        EspScreenshotTool.takeWithName(pictureName);

        download(pictureName);
        double percentage = EspScreenshotTool.comparePercentage(
                downloadLocation(pictureName),
                EspScreenshotTool.screenshotLocation(pictureName).getAbsolutePath());

        if(throwError) {
            assertEquals(100.0, percentage, EspScreenshotTool.COMPARE_DELTA_TIME_CHANGE + COMPARE_DELTA_DATE_CHANGE);
        }
    }
}
