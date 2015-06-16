package de.nenick.quacc.backup;

import android.content.Context;
import android.os.Environment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;

@EBean
public class WriteDataFunction {

    @RootContext
    Context context;

    private File appDatabase;
    private File backupDatabase;

    @AfterInject
    protected void setup() {
        appDatabase = context.getDatabasePath(QuAccSQLiteOpenHelper.DATABASE_FILE_NAME);
        backupDatabase = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/database-quacc.db");
    }

    public void importDatabase() {

        copyFileToLocation(backupDatabase, appDatabase);
    }

    public void exportDatabase() {

        copyFileToLocation(appDatabase, backupDatabase);
    }

    private void copyFileToLocation(File sourceFile, File targetFile) {
        QuAccSQLiteOpenHelper sqlite = QuAccSQLiteOpenHelper.getInstance(context);

        // Close the SQLiteOpenHelper so it will commit the created empty
        // database to internal storage.
        sqlite.close();
            try {
                copyFile(sourceFile, targetFile);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Access the copied database so SQLiteHelper will cache it and mark
            // it as created.
            sqlite.getWritableDatabase().close();
    }

    // original from package android.os.FileUtils;
    public static void copyFile(File srcFile, File destFile) throws IOException {

            InputStream in = new FileInputStream(srcFile);
            try {
                copyToFile(in, destFile);
            } finally  {
                in.close();
            }
    }

    // original from package android.os.FileUtils;
    public static void copyToFile(InputStream inputStream, File destFile) throws IOException {
            if (destFile.exists()) {
                destFile.delete();
            }
            FileOutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.flush();
                try {
                    out.getFD().sync();
                } catch (IOException e) {
                }
                out.close();
            }


    }
}
