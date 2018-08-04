package com.hydrozagadka.Beans;

import com.hydrozagadka.servlets.LoadServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.InputStream;

@Stateless
public class LoadZipToDatabaseBean {

    private static Logger logger = LoggerFactory.getLogger(LoadServlet.class);

    private static final String DIRECT_PATH = "/opt/jboss/patataje-upload";
    @Inject
    private UnzipDao unzipDao;


    public void unzipFile(InputStream is){
        unzipDao.unzip(is, DIRECT_PATH);
        JJD4PAT-10-TestyJednostkowe
        logger.info("Rozpakowywanie pliku: {}");
        CSVLoader csvLoader = new CSVLoader();
        waterContainerMap = csvLoader.getAllContainers();

    }
}
