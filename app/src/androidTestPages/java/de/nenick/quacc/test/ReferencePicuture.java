package de.nenick.quacc.test;

import de.nenick.espressomacchiato.tools.EspScreenshotTool;

public class ReferencePicuture {

    public static void take(String pictureName) {
        pictureName = "reference_" + pictureName.replace(" ", "_");
        EspScreenshotTool.takeWithName(pictureName);
    }
}
