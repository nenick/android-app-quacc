package de.nenick.quacc.test;

import android.Manifest;
import android.os.Environment;
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

    public static void download(String name) {
        try {

            InputStream in = new java.net.URL(screenshotUrl + name).openStream();

            OutputStream output = new FileOutputStream(InstrumentationRegistry.getTargetContext().getFilesDir().toString() + "/" + name);

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

        ReferencePicuture.download(pictureName + ".png");

        double percentage = EspScreenshotTool.comparePercentage(
                new EspScreenshotTool().obtainScreenshotDirectory() + pictureName + ".png",
                InstrumentationRegistry.getTargetContext().getFilesDir() + "/" + pictureName + ".png");

        if(throwError) {
            assertEquals(100.0, percentage, EspScreenshotTool.COMPARE_DELTA_TIME_CHANGE);
        }
    }
}
