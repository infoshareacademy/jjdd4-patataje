package com.hydrozagadka.Beans;

import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;
import com.hydrozagadka.servlets.LoadServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.InputStream;
import java.util.Map;

@Stateless
public class LoadZipToDatabaseBean {

    private static Logger logger = LoggerFactory.getLogger(LoadServlet.class);

    private static final String DIRECT_PATH = "/home/pawelorlikowski/jjdd4-patataje/HydroZagadkaApp/data";
    @Inject
    private UnzipDao unzipDao;

    @Inject
    private FreeMarkerConfig freeMarkerConfig;

    private Map<Long, WaterContainer> waterContainerMap;

    public void unzipFile(InputStream is){
        unzipDao.unzip(is, DIRECT_PATH);
        logger.info("Unzip File: {}");
        CSVLoader csvLoader = new CSVLoader();
        waterContainerMap = csvLoader.getAllContainers();
    }
}
