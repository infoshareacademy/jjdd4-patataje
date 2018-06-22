package com.hydrozagadka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LoadFile implements Loadable {
    private BufferedReader br;

    private Map<Integer, WaterContainer> allContainers = new HashMap<>();
    private WaterContainer waterContainer;

    public Map<Integer, WaterContainer> getAllContainers() {
        return allContainers;
    }

    public WaterContainer getWaterContainer() {
        return waterContainer;
    }

    private List<String> getFilesList(String directory) {
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory))) {
            for (Path path : directoryStream) {
                if (path.toString().contains("codz_")) {
                    fileNames.add(path.toString());
                }
            }
        } catch (IOException ex) {

        }
        return fileNames;
    }

    private WaterContainer createWaterContainer(String[] a) {
        Integer id = Integer.parseInt(a[0].replaceAll(" ", ""));
        String containerName = a[1].toUpperCase();
        String stationName = a[2];
        String province = "brak";

        if (a.length == 11) {
            province = a[10];
        }

        return new WaterContainer(id, containerName, stationName, province, new ArrayList<History>());
    }

    private History createHistory(String[] a) {
        Integer year = Integer.parseInt(a[3]);
        Integer month = Integer.parseInt(a[9]);
        Integer day = Integer.parseInt(a[5]);

        LocalDate date = LocalDate.of(year, month, day);

        Double waterDeep = Double.parseDouble(a[6]);
        Double flow = Double.parseDouble(a[7]);
        Double temperature = Double.parseDouble(a[8]);

        return new History(date, waterDeep, flow, temperature);
    }

    //Metod loading all csv files into objects
    public Map<Integer, WaterContainer> load() throws IOException {

        String loadedLine;
        String[] splitedLine;

        List<String> files = getFilesList("data/");

        for (String file : files) {
            br = new BufferedReader(new FileReader(file));
            //read lines
            while ((loadedLine = br.readLine()) != null) {
                //deleting "
                loadedLine = loadedLine.replaceAll("\"", "");
                //split data
                splitedLine = loadedLine.split(",");
                //creating local variables with splited data in String table

                WaterContainer wc = createWaterContainer(splitedLine);
                History history = createHistory(splitedLine);

                //if object doesn't exist in map
                if (!allContainers.containsKey(wc.getId())) {
                    allContainers.put(wc.getId(), wc);
                } else {
                    WaterContainer existingWc = allContainers.get(wc.getId());
                    if (existingWc.getProvince().equals("brak") && !wc.getProvince().equals("brak")) {
                        existingWc.setProvince(wc.getProvince());
                    }
                }

                allContainers.get(wc.getId()).getHistory().add(history);
            }
        }
        return allContainers;
    }

    public void readExample() {
        WaterContainer wt = allContainers.get(153220190);
        System.out.println("====================================================================");
        System.out.println("| " + wt.getContainerName() + " |     " + wt.getProvince() + "                |        " + wt.getStationName());
        System.out.println("====================================================================\n");
        System.out.println("| data      | stan wody | przeplyw | temperatura");
        System.out.println("------------------------------------------------");

        wt.getHistory().stream().sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate())).forEach(hs -> {
            System.out.print(hs.getDate() + " |     " + hs.getWaterDeep() + "      |    " + hs.getFlow() + "    |   " + hs.getTemperature() + " |\n");
            System.out.println("------------------------------------------------");
        });

    }
}
