package fr.oxal.v2.waven.utils.jsonCreator;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileMaker {


    public static void makeFile(String source, String destination) throws IOException {
        InputStream fis = null;
        FileOutputStream fos = null;

        try {
            byte[] buffer = new byte[1024];
            int taille = 0;

            fis = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
            fos = new FileOutputStream(destination);
            while ((taille = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, taille);
            }
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
