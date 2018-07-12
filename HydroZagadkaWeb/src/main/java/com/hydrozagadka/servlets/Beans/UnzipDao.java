package com.hydrozagadka.servlets.Beans;

import javax.ejb.Local;
import java.io.FileInputStream;
import java.io.InputStream;

@Local
public interface UnzipDao {
    void unzip(InputStream fis, String destinationPath);
}
