package de.nenick.quacc.test;

import android.Manifest;
import android.os.Environment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.nenick.espressomacchiato.testbase.EspressoTestBase;
import de.nenick.espressomacchiato.tools.EspPermissionsTool;
import de.nenick.espressomacchiato.tools.EspScreenshotTool;

import static org.junit.Assert.assertEquals;

public class ReferencePicuture {

    public static final String screenshotUrl = "https://circle-artifacts.com/gh/nenick/QuAcc/223/artifacts/0/tmp/circle-junit.N0yV7Z1/test-screenshots/";

    public static void download(String name) {
        try {

            InputStream in = new java.net.URL(screenshotUrl + name).openStream();

            OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/" + name);

            byte data[] = new byte[1024];

            long total = 0;

            int count;
            while ((count = in.read(data)) != -1) {
                total += count;
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

    public static void check(String pictureName) {
        EspPermissionsTool.ensurePermissions(EspressoTestBase.currentActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        pictureName = "reference_" + pictureName.replace(" ", "_");
        EspScreenshotTool.takeWithName(pictureName);

        //*
        ReferencePicuture.download(pictureName + ".png");

        double percentage = EspScreenshotTool.comparePercentage(
                new EspScreenshotTool().obtainScreenshotDirectory() + pictureName + ".png",
                Environment.getExternalStorageDirectory().toString() + "/" + pictureName + ".png");

        assertEquals(100.0, percentage, EspScreenshotTool.COMPARE_DELTA_TIME_CHANGE);
        //*/
    }
}
