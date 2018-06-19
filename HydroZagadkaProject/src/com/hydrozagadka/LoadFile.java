package com.hydrozagadka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LoadFile {
    BufferedReader br;
    Map<Integer, WaterContainer> allContainers = new HashMap<>();

        //Metod loading all csv files into objects
    public void load() throws IOException {
        String loadedLine;
        WaterContainer wc = null;
        String[] splitedLine;

        for (int i = 1; i <= 12; i++) {
            br = new BufferedReader(new FileReader("/home/orzel/jjdd4-patataje/HydroZagadkaProject/codz_2016_"+i+".csv"));
            //read lines
        while ((loadedLine = br.readLine()) != null) {
            //deleting "
            loadedLine = loadedLine.replaceAll("\"", "");
            //deleting spaces
            loadedLine = loadedLine.replaceAll(" ", "");
            //split data
            splitedLine = loadedLine.split(",");
            //creating local variables with splited data in String table
            Integer id = Integer.parseInt(splitedLine[0]);
            String containerName = splitedLine[1];
            String stationName = splitedLine[2];
            Integer year = Integer.parseInt(splitedLine[3]);
            Integer month = Integer.parseInt(splitedLine[4]);
            Integer day = Integer.parseInt(splitedLine[5]);
            Double waterDeep = Double.parseDouble(splitedLine[6]);
            Double flow = Double.parseDouble(splitedLine[7]);
            Double temperature = Double.parseDouble(splitedLine[8]);
            //change flow from none to 0;
            if (flow == 99999.999) flow = 0.0;
            //if object doesn't exist in map
            if (!allContainers.containsKey(id)) {
                wc = new WaterContainer(id, containerName, stationName, new ArrayList<>());
                wc.history.add(new History(year, month, day, waterDeep, flow, temperature));
                //put id and object into map
                allContainers.put(id, wc);
            } else {
                allContainers.get(id).history.add(new History(year, month, day, waterDeep, flow, temperature));
            }
        }
    }
    }

    public void readExample() {
            WaterContainer wt = allContainers.get(149180020);
        System.out.println("------------------------------------------------------------------");
            System.out.println("| "+wt.getContainerName() + " |");
            System.out.println("--------------------------------------------------------------");
            for (History hs : wt.history) {
                System.out.print("Date: "+hs.getYear()+"/" + hs.getMonth()+"/"+hs.getDay()+" \t| flow: "+hs.getFlow()+" \t| temperature:"+hs.getTemperature()+" \t| deep: "+hs.getWaterDeep()+"\n");
            }
            System.out.println();

    }
}
