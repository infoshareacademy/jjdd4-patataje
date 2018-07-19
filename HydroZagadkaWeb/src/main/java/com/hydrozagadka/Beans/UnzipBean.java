package com.hydrozagadka.Beans;

import javax.enterprise.context.RequestScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RequestScoped
public class UnzipBean implements UnzipDao {

    @Override
    public void unzip(InputStream fis, String destinationDirPath) {
        File dir = new File(destinationDirPath);
        if (!dir.exists()) dir.mkdirs();
        byte[] buffer = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(fis);) {
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(destinationDirPath + File.separator + fileName);
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}