package com.hydrozagadka.servlets.Beans;

import javax.ejb.Local;

@Local
public interface UnzipDao {
    void unzip(String zipFilePath,String destinationPath);
}
