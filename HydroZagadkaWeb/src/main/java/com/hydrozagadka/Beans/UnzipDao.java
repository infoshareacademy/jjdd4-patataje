package com.hydrozagadka.Beans;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.InputStream;

@Local
public interface UnzipDao {
    void unzip(InputStream fis, String destinationPath);
}
