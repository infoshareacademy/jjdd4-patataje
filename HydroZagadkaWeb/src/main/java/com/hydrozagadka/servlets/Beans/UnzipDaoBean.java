package com.hydrozagadka.servlets.Beans;

import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@ApplicationScoped
public class UnzipDaoBean implements UnzipDao {

    @Override
    public void unzip(InputStream fis, String destinationDirPath) {
        File dir = new File(destinationDirPath);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destinationDirPath + File.separator + fileName);
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

