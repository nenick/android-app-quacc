package de.nenick.quacc.core.backup;

import org.androidannotations.annotations.EBean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@EBean
public class GetOutputStreamToFileFunction {

    public OutputStream apply(String path) {
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return stream;
    }
}
