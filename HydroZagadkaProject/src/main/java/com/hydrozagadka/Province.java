package com.hydrozagadka;


import de.vandermeer.asciitable.AsciiTable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Properties;

public class Province {
    private static List<String> province;
    private static CSVLoader csvLoader = new CSVLoader();
    private static FilterFiles filterFiles = new FilterFiles(csvLoader);
    private static DecimalFormat doubleShowFormat;
    private static DateTimeFormatter dateFormat;

    public static void setDoubleShowFormat(DecimalFormat doubleShowFormat) {
        Province.doubleShowFormat = doubleShowFormat;
    }

    public static void setDateFormat(DateTimeFormatter dateFormat) {
        Province.dateFormat = dateFormat;
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
        at.getContext().setWidth(50);
        System.out.println(at.render());
    }

    public static void secondMenu() {
        AsciiTable secmenu = new AsciiTable();
        for (int i = 0; i < 5; i++) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        secmenu.addRule();
        secmenu.addRow("1:Wyswietl Stacje", "2:Wybierz Zbiornik", "3:Cofnij", "0:Wyjscie");
        secmenu.addRule();
        secmenu.getContext().setWidth(70);
        System.out.println(secmenu.render());
    }

    public static void casemenu(Integer choice, Integer choice2) {
        AsciiTable ac = new AsciiTable();
        switch (choice2) {
            case 1: {
                List<WaterContainer> filteredByProvince = filterFiles.showWaterContainersThroughProvince(province.get(choice - 1));
                ac.addRule();
                ac.addRow("ID", "NAZWA STACJI", "NAZWA ZBIORNIKA");
                ac.addRule();
                for (WaterContainer wt : filteredByProvince) {
                    ac.addRow(wt.getId(), wt.getStationName(), wt.getContainerName());
                    ac.addRule();
                }
                ac.addRule();
                ac.addRow("Podaj ID", "3:Cofnij", "0:Wyjscie");
                ac.addRule();
                ac.getContext().setWidth(70);
                System.out.println(ac.render());
                break;
            }
        }
    }

    public static void getproperties(){
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("data/config.properties");
            // load a properties file
            prop.load(input);
            setDoubleShowFormat(new DecimalFormat(prop.getProperty("doubleFormat")));
            setDateFormat(DateTimeFormatter.ofPattern(prop.getProperty("dateFormat")));

        } catch (IOException ex) {
            System.out.println("Nie znaleziono pliku konfiguracyjnego!");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("Nieudane zamkniecie pliku konfiguracyjnego");
                }
            }
        }
    }

    public static void main(String[] args) {

        getproperties();
        Scanner scanner = new Scanner(System.in);
        province = csvLoader.getProvince().stream().collect(Collectors.toList());
        createMenu();
        Integer choice = -1;
        while (choice != 0) {

            String keyboardChoice = scanner.next();
            try {
                choice = Integer.parseInt(keyboardChoice);
            } catch (NumberFormatException e) {
                System.out.println("Podales niewlasciwy ciag znakow!");
            }
            if (choice <= 16 && choice >= 1) {
                secondMenu();
                try {
                    Integer choice2 = scanner.nextInt();
                    casemenu(choice, choice2);

                        // to jest kod do wyswietlania doubli w formacie z propertiesow
//                        String dx = doubleShowFormat.format(h.getTemperature());
//                        double d = Double.valueOf(dx);

                } catch (Exception e) {
                    System.out.println("Podales niewlasciwy znak!");
                }
            }
        }
    }
}