package com.hydrozagadka;

import com.hydrozagadka.exceptions.DataLengthException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class CSVLoader {
    private static final String DIRECT_PATH = "/home/pawelorlikowski/jjdd4-patataje/HydroZagadkaApp/data";

    private BufferedReader br;
    private Set<String> province = new LinkedHashSet<>();
    private Map<Long, WaterContainer> allContainers = new HashMap<>();

    public Map<Long, WaterContainer> getAllContainers() {
        return allContainers;
    }

    public CSVLoader() {
        loadCSV();
    }

    private List<String> getFilesList() {
        List<String> fileNames = new ArrayList<>();
        try  {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(DIRECT_PATH));
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
        Long id = Long.parseLong(a[0].replaceAll(" ", ""));
        String containerName = a[1].toUpperCase();
        String stationName = a[2];
        String province = "N/A";
        if (a.length == 11) {
            province = a[10];
        }
        return new WaterContainer(id, containerName, stationName, province, new ArrayList<History>());
    }

    private History createHistory(WaterContainer wc, String[] a) {
        try {
            Integer year = Integer.parseInt(a[3]);
            Integer month = Integer.parseInt(a[9]);
            Integer day = Integer.parseInt(a[5]);
            LocalDate date = LocalDate.of(year, month, day);
            Double waterDeep = Double.parseDouble(a[6]);
            Double flow = Double.parseDouble(a[7]);
            if (flow == 99999.999) {
                flow = 0.0;
            }
            Double temperature = Double.parseDouble(a[8]);
            if (temperature == 99.9){
                temperature=0.0;
            }
                History history = new History(date, waterDeep, flow, temperature);
            history.setContainerId(wc.getId());
            return history;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Błąd podczas konwertowania rekordu na liczbę!");
            System.exit(0);
        }
        return null;
    }

    private Map<Long, WaterContainer> loadCSV() {
        String loadedLine;
        String[] splitedLine;
        try {
            List<String> files = getFilesList();
            for (String file : files) {
                br = new BufferedReader(new FileReader(file));
                while ((loadedLine = br.readLine()) != null) {
                    splitedLine = splitString(loadedLine);
                    WaterContainer wc = createWaterContainer(splitedLine);
                    History history = createHistory(wc, splitedLine);
                    checkingExistingContainers(wc, history);
                }
            }
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku!");
        }
        return allContainers;
    }

    private String[] splitString(String loadedLine) {
        String[] splitedLine;//deleting "
        loadedLine = loadedLine.replaceAll("\"", "");
        //split data
        splitedLine = loadedLine.split(",");
        if (splitedLine.length < 10) throw new DataLengthException("Plik ma nieodpowiednią liczbę rekordów!");
        return splitedLine;
    }

    private void checkingExistingContainers(WaterContainer wc, History history) {
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

  public Set<String> getProvince() {
        return province;
    }
}
