package com.hydrozagadka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class CSVLoader{
    private BufferedReader br;
    private Set<String> province = new LinkedHashSet<>();

    private List<String> getFilesList(String directory) {
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory))) {
            for (Path path : directoryStream) {
                if (path.toString().contains("codz_")) {
                    fileNames.add(path.toString());
                }
            }
        } catch (IOException ex) {
            System.out.println("Nie znaleziono folderu!");
        }
        return fileNames;
    }
    private WaterContainer createWaterContainer(String[] a) {
        Integer id = Integer.parseInt(a[0].replaceAll(" ", ""));
        String containerName = a[1].toUpperCase();
        String stationName = a[2];
        String province = "N/A";
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
    public Map<Integer, WaterContainer> load() {
        String loadedLine;
        String[] splitedLine;

        Map<Integer, WaterContainer> allContainers = new HashMap<>();
        try {
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
                        if (existingWc.getProvince().equals("N/A") && !wc.getProvince().equals("N/A")) {
                            existingWc.setProvince(wc.getProvince());
                            province.add(wc.getProvince());
                        }
                    }
                    allContainers.get(wc.getId()).getHistory().add(history);
                }
            }
        } catch (Exception e) {
            System.out.println("Nie znaleziono pliku!");
        }
        return allContainers;
    }

    public Set<String> getProvince(){
        return province;
    }
}
