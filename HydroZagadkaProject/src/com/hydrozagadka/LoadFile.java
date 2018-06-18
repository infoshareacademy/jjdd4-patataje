package com.hydrozagadka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadFile {
    BufferedReader br;
    Map<Integer,WaterContainer> allContainers = new HashMap<>();

    LoadFile() throws FileNotFoundException {
        br = new BufferedReader(new FileReader("/home/orzel/jjdd4-patataje/HydroZagadkaProject/plik.csv"));
    }
    public void load() throws IOException {
        String loadedLine;
        WaterContainer wc = null;
        String[] splitedLine;
        List<History> helperList = new ArrayList<>();
        while((loadedLine=br.readLine())!=null){
            loadedLine = loadedLine.replaceAll("\"","");
            splitedLine = loadedLine.split(",");
            splitedLine[0]=splitedLine[0].trim();
           if(!allContainers.containsKey(Integer.parseInt(splitedLine[0]))){
               wc = new WaterContainer(Integer.parseInt(splitedLine[0]),splitedLine[1],splitedLine[2],helperList);


               allContainers.put(Integer.parseInt(splitedLine[0]),wc);

               helperList.add(new History(Integer.parseInt(splitedLine[3]),Integer.parseInt(splitedLine[4]), 
                       Integer.parseInt(splitedLine[5]),Double.parseDouble(splitedLine[6]),Double.parseDouble(splitedLine[7]),Double.parseDouble(splitedLine[8]),wc));
           }else{
               helperList.add(new History(Integer.parseInt(splitedLine[3]),Integer.parseInt(splitedLine[4]),
                       Integer.parseInt(splitedLine[5]),Double.parseDouble(splitedLine[6]),Double.parseDouble(splitedLine[7]),Double.parseDouble(splitedLine[8]),wc));
   //            helperList.removeAll(helperList);
           }
        }
    }

    public void readExample(){
           WaterContainer wt = allContainers.get(149180020);
            System.out.println(wt.containerName+" ");
            System.out.println("==================");
            for (History hs: wt.history) {
                System.out.print(" "+hs.waterDeep);
            }
            System.out.println();

    }
}
