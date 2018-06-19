package com.hydrozagadka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LoadFile {
    BufferedReader br;
    Map<Integer, WaterContainer> allContainers = new HashMap<>();

    LoadFile() throws FileNotFoundException {
        br = new BufferedReader(new FileReader("plik.csv"));
    }

    public void load() throws IOException {
        String loadedLine;
        WaterContainer wc = null;
        String[] splitedLine;

        while ((loadedLine = br.readLine()) != null) {
            loadedLine = loadedLine.replaceAll("\"", "");
            splitedLine = loadedLine.split(",");
            splitedLine[0] = splitedLine[0].trim();

            if (!allContainers.containsKey(Integer.parseInt(splitedLine[0]))) {
                wc = new WaterContainer(Integer.parseInt(splitedLine[0]), splitedLine[1], splitedLine[2], new ArrayList<>());
                wc.history.add(new History(Integer.parseInt(splitedLine[3]), Integer.parseInt(splitedLine[4]),
                        Integer.parseInt(splitedLine[5]), Double.parseDouble(splitedLine[6]), Double.parseDouble(splitedLine[7]), Double.parseDouble(splitedLine[8])));

                allContainers.put(Integer.parseInt(splitedLine[0]), wc);
            } else {
                allContainers.get(Integer.parseInt(splitedLine[0])).history.add(new History(Integer.parseInt(splitedLine[3]), Integer.parseInt(splitedLine[4]),
                        Integer.parseInt(splitedLine[5]), Double.parseDouble(splitedLine[6]), Double.parseDouble(splitedLine[7]), Double.parseDouble(splitedLine[8])));
//                helperList.add(new History(Integer.parseInt(splitedLine[3]), Integer.parseInt(splitedLine[4]),
//                        Integer.parseInt(splitedLine[5]), Double.parseDouble(splitedLine[6]), Double.parseDouble(splitedLine[7]), Double.parseDouble(splitedLine[8]), wc));
                //            helperList.removeAll(helperList);
            }
        }
    }

    public void readExample() {
        for (WaterContainer wt: allContainers.values()) {


            System.out.println(wt.containerName + " ");
            System.out.println("==================");
            for (History hs : wt.history) {
                System.out.print(" " + hs.waterDeep);
            }
            System.out.println();
        }
    }
}
