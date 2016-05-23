package de.nenick.quacc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collection;

import de.nenick.espressomacchiato.tools.EspScreenshotTool;

@RunWith(Parameterized.class)
public class ReferencePicturesTest {

    private String picture;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<String> data() {
        return Arrays.asList(new File(new EspScreenshotTool().obtainScreenshotDirectory()).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.contains("reference_");
            }
        }));
    }

    public ReferencePicturesTest(String picture) {

        this.picture = picture;
    }

    @Test
    public void checkPicture() {
        ReferenceCheck.check(picture);
    }
}
