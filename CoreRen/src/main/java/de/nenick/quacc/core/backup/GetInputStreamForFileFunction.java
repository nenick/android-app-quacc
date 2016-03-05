package de.nenick.quacc.core.backup;

import org.androidannotations.annotations.EBean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@EBean
public class GetInputStreamForFileFunction {

    public InputStream apply(String path) {
        FileInputStream stream;
        try {
            stream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return stream;
    }
}
