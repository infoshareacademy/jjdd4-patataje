package com.hydrozagadka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LoadFile implements Loadable{
  private  BufferedReader br;

  private  Map<Integer, WaterContainer> allContainers = new HashMap<>();
  private  WaterContainer waterContainer;

    public Map<Integer, WaterContainer> getAllContainers() {
        return allContainers;
    }

    public WaterContainer getWaterContainer() {
        return waterContainer;
    }


        //Metod loading all csv files into objects
    public  Map<Integer, WaterContainer> load() throws IOException {

        String loadedLine;
        WaterContainer wc = null;
        String[] splitedLine;

        for (int i = 13; i >= 1; i--) {
            br = new BufferedReader(new FileReader("data/codz_2016_"+i+".csv"));
            //read lines
        while ((loadedLine = br.readLine()) != null) {
            //deleting "
            loadedLine = loadedLine.replaceAll("\"", "");
            //split data
            splitedLine = loadedLine.split(",");
            //creating local variables with splited data in String table
            Integer id = Integer.parseInt(splitedLine[0].replaceAll(" ",""));
            String containerName = splitedLine[1].toUpperCase();
            String stationName = splitedLine[2];
            Integer year = Integer.parseInt(splitedLine[3]);
            Integer month = Integer.parseInt(splitedLine[9]);
            Integer day = Integer.parseInt(splitedLine[5]);
            Double waterDeep = Double.parseDouble(splitedLine[6]);
            Double flow = Double.parseDouble(splitedLine[7]);
            Double temperature = Double.parseDouble(splitedLine[8]);
            String province ="brak";
            if(splitedLine.length==11) province=splitedLine[10];
            //change flow from none to 0;
            if (flow == 99999.999) flow = 0.0;
            //if object doesn't exist in map
            if (!allContainers.containsKey(id)) {
                wc = new WaterContainer(id, containerName, stationName,province, new ArrayList<History>());
                wc.getHistory().add(new History(year, month, day, waterDeep, flow, temperature));
                //put id and object into map
                allContainers.put(id, wc);
            } else {
                allContainers.get(id).getHistory().add(new History(year, month, day, waterDeep, flow, temperature));
            }
        }
    }
    return allContainers;
    }
    public void readExample() {
        WaterContainer wt = allContainers.get(150180060);
            System.out.println("====================================================================");
            System.out.println("| "+wt.getContainerName() + " |     "+wt.getProvince()+"                |        "+wt.getStationName());
            System.out.println("====================================================================\n");
        System.out.println("| data      | stan wody | przeplyw | temperatura");
        System.out.println("------------------------------------------------");
        for (History hs: wt.getHistory()) {
            System.out.print(hs.getYear()+"/"+hs.getMonth()+"/"+hs.getDay()+" |     "+hs.getWaterDeep()+"      |    "+hs.getFlow()+"    |   "+hs.getTemperature()+" |\n");
            System.out.println("------------------------------------------------");
        }
    }
}
