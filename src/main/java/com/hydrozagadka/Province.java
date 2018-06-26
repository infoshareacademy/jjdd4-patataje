package com.hydrozagadka;


import de.vandermeer.asciitable.AsciiTable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Province {
    private static List<String> province;
    private static CSVLoader csvLoader = new CSVLoader();
    private static FilterFiles filterFiles = new FilterFiles(csvLoader);
    private static Map<Integer, WaterContainer> allFiles;
    private static Scanner scanner = new Scanner(System.in);
    private static DecimalFormat doubleFormat;
    private static DateTimeFormatter dateFormat;

    private static void getProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("data/config.properties");
            prop.load(input);
            doubleFormat = new DecimalFormat(prop.getProperty("doubleformat"));
            dateFormat = DateTimeFormatter.ofPattern(prop.getProperty("dateformat"));

        } catch (IOException ex) {
            System.out.println("Blad przy wczytywaniu pliku konfiguracyjnego");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("Blad przy probie zamkniecia pliku konfiguracyjnego");
                }
            }
        }
    }


    public static void createMenu() {
        AsciiTable at = new AsciiTable();
        System.out.println("\n\nAPLIKACJA SPRAWDZAJACA STAN WOD W POLSCE\n");
        at.addRule();
        at.addRow("MENU");
        at.addRule();
        for (int i = 1; i < province.size(); i++) {
            at.addRow(i + ": " + province.get(i - 1));
            at.addRule();
        }
        at.addRow("0: Wyjscie");
        at.addRule();
        at.getContext().setWidth(35);
        System.out.println(at.render());
        try {
            int choice = Integer.valueOf(scanner.nextLine());
            if (choice <= 16 && choice >= 1) {
                secondMenu(province.get(choice - 1));
            }else{
                System.out.println("Podaj liczbe z zakresu w menu!");
                createMenu();
            }
        }catch (NumberFormatException e){
            System.out.println("Podaj poprawna wartosc!");
            createMenu();
        }
    }

    public static void secondMenu(String province) {
        AsciiTable secmenu = new AsciiTable();
//       TU ENTER
        secmenu.addRule();
        secmenu.addRow("1:Wyswietl Stacje", "2:Wybierz Zbiornik", "3:Cofnij", "0:Wyjscie");
        secmenu.addRule();
        secmenu.getContext().setWidth(70);
        System.out.println(secmenu.render());
        int choice = Integer.valueOf(scanner.nextLine());
        switch (choice) {
            case 1: {
                casemenu(province);
                break;
            }
            case 2: {
                chooseContainerWithName();
            }
        }


    }

    public static void casemenu(String province) {
        AsciiTable ac = new AsciiTable();
        List<WaterContainer> filteredByProvince = filterFiles.showWaterContainersThroughProvince(province);
        ac.addRule();
        ac.addRow("ID", "NAZWA STACJI", "NAZWA ZBIORNIKA");
        ac.addRule();
        for (WaterContainer wt : filteredByProvince) {
            ac.addRow(wt.getId(), wt.getStationName(), wt.getContainerName());
            ac.addRule();
        }

        ac.getContext().setWidth(70);
        System.out.println(ac.render());
        getIDMenu();
    }

    public static void getIDMenu() {
        AsciiTable getIDM = new AsciiTable();
        getIDM.addRule();
        getIDM.addRow("Podaj ID ", "3:Cofnij", "0:Wyjscie");
        getIDM.addRule();
        getIDM.getContext().setWidth(70);
        System.out.println(getIDM.render());

//        Integer choiceID = Integer.parseInt(scanner.nextLine());

    }


    public static void chooseContainerWithName() {
        AsciiTable ccwID = new AsciiTable();
        AsciiTable ccwID2 = new AsciiTable();
        ccwID.addRule();
        ccwID.addRow("Podaj nazwe zbiornika", "3:Cofnij", "0:Wyjscie");
        ccwID.addRule();
        ccwID.getContext().setWidth(70);
        System.out.println(ccwID.render());
        String choice = scanner.nextLine();
        List<WaterContainer> containers = filterFiles.filterThroughContainer(choice);
        for (int i = 0; i < containers.size(); i++) {
            WaterContainer wt = containers.get(i);
            ccwID2.addRule();
            ccwID2.addRow(wt.getId(), wt.getProvince(), wt.getStationName(), wt.getContainerName());
            if(i%11==10){
                ccwID2.addRule();
                ccwID2.getContext().setWidth(70);
                System.out.println(ccwID2.render());
                System.out.println("wyswietlic wiecej danych? t/n");
                String loadMoreFiles = scanner.nextLine();
                if(loadMoreFiles.equals("n")) break;
            }
        }
//        String choiceName = scanner.nextLine();
    }

    public static void showNewestData(int id) {
        int lastIndexOfHistory = allFiles.get(id).getHistory().size() - 1;
        AsciiTable sNd = new AsciiTable();
        sNd.addRule();
        sNd.addRow("NAZWA ZBIORNIKA", "NAZWA STACJI", "WOJEWÓDZTWO", "DATA", "STAN WODY", "PRZEPŁYW", "TEMPERATURA");
        sNd.addRule();
        sNd.addRow(allFiles.get(id).getContainerName(),
                allFiles.get(id).getStationName(), allFiles.get(id).getProvince(),
                allFiles.get(id).getHistory().get(lastIndexOfHistory).getDate(),
                allFiles.get(id).getHistory().get(lastIndexOfHistory).getWaterDeep(),
                allFiles.get(id).getHistory().get(lastIndexOfHistory).getFlow(),
                allFiles.get(id).getHistory().get(lastIndexOfHistory).getTemperature());
        System.out.println(sNd.render());
    }

    public static void showHistoricData(int id) {
        WaterContainer wt = allFiles.get(id);
        AsciiTable sHd = new AsciiTable();
        sHd.addRule();
        sHd.addRow("STACJA: ", wt.getContainerName(), wt.getProvince(), wt.getStationName());
        sHd.addRule();
        sHd.addRow("DATA", "STAN WODY", "PRZEPŁYW", "TEMPERATURA");
        sHd.addRule();
        wt.getHistory().stream()
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .forEach(hs -> {
                    sHd.addRow(hs.getDate(), hs.getWaterDeep(), hs.getFlow(), hs.getTemperature());
                    sHd.addRule();
                });
        System.out.println(sHd.render());

    }

    public static void main(String[] args) {
        province = csvLoader.getProvince().stream().collect(Collectors.toList());
        getProperties();
             createMenu();

        //print doubli z propertiesa przyklad
//        double b= 12.123221;
//        String a = doubleFormat.format(b);
//        b= Double.parseDouble(a);
//        System.out.println(b);

        //print LocalDate z propertiesa
//    LocalDate ld = LocalDate.now();
//    String a = ld.format(dateFormat);
//        System.out.println(a);
    }


}
