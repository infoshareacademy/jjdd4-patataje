package com.hydrozagadka;


import com.hydrozagadka.exceptions.IdLengthException;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Province {
    private static List<String> province;
    private static CSVLoader csvLoader = new CSVLoader();
    private static FilterFiles filterFiles = new FilterFiles(csvLoader);
    private static Scanner scanner = new Scanner(System.in);
    private static DecimalFormat doubleFormat;
    private static DateTimeFormatter dateFormat;
    private static String ANSI_COLOR = null;

    private static void getProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("data/config.properties");
            prop.load(input);
            doubleFormat = new DecimalFormat(prop.getProperty("doubleformat"));
            dateFormat = DateTimeFormatter.ofPattern(prop.getProperty("dateformat"));
            ANSI_COLOR = prop.getProperty("cyan");

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

    public static void closeApp() {

        System.out.println("Aplikacja została zamknięta.");
        System.exit(0);
    }

    public static void createMenu() {

        AsciiTable at = new AsciiTable();
        System.out.println(ANSI_COLOR + "\n\nAPLIKACJA WYŚWIETLAJĄCA STAN WÓD DLA POLSKICH RZEK\n");
        System.out.println("Aby wyświetlić najświeższe dostępne dane:");
        try {
        at.addRule();
        at.addRow(null,null, null, "Wybierz województwo:").setTextAlignment(TextAlignment.CENTER);;
        at.addRule();
        for (int i = 0, j=1; i < province.size()-4; i+=4,j+=4) {
            at.addRow(j + ": " + province.get(i), (j + 1) + ": " + province.get(i+1),
                    (j + 2) + ": " + province.get(i + 2), (j + 3) + ": " + province.get(i + 3));
            at.addRule();
        }
        at.addRow(null,null, null, "0: Wyjście").setTextAlignment(TextAlignment.RIGHT);;
        at.addRule();
        at.getContext().setWidth(170);
        System.out.println(at.render());
            int choice = Integer.valueOf(scanner.nextLine());

            if (choice <= 16 && choice >= 1) {
                selectionMenu(province.get(choice - 1));
            } else if (choice == 0) {
                closeApp();
            } else {
                System.out.println("Podaj poprawną liczbę województwa.");
                System.out.println("Naciśnij Enter aby przejść dalej");
                scanner.nextLine();
                createMenu();
            }
        } catch (NumberFormatException e) {
            System.out.println("To nie cyferki, spróbuj jeszcze raz.!");
            System.out.println("Naciśnij Enter aby przejść dalej");
            scanner.nextLine();
            createMenu();
        }catch (IndexOutOfBoundsException e){
            System.out.println("Blad podczas wczytywania danych program zostanie zamkniety");
            closeApp();
        }
    }

    public static void selectionMenu(String province) {
        AsciiTable secmenu = new AsciiTable();
//Runtime.getRuntime().exec("cls");
        secmenu.addRule();
        secmenu.addRow("1:Wyświetl Stacje", "2:Wybierz Rzekę", "3:Cofnij", "0:Wyjście").setTextAlignment(TextAlignment.CENTER);;
        secmenu.addRule();
        secmenu.getContext().setWidth(70);
        System.out.println(secmenu.render());
        try {
            int choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1: {
                    showStationsforProvinces(province);
                    break;
                }
                case 2: {
                    chooseContainerWithName(province);
                    break;
                }
                case 3: {
                    createMenu();
                    break;
                }
                case 0: {
                    closeApp();
                    break;
                }
                default: {
                    System.out.println("Wybrałeś złą odpowiedź, spróbuj jeszcze raz.");
                    selectionMenu(province);
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Podaj liczbe!");
            selectionMenu(province);
        }
    }

    public static void showStationsforProvinces(String province) {
        AsciiTable ac = new AsciiTable();
        List<WaterContainer> filteredByProvince = filterFiles.showWaterContainersThroughProvince(province);
        ac.addRule();
        ac.addRow("ID", "NAZWA STACJI", "NAZWA RZEKI").setTextAlignment(TextAlignment.CENTER);;
        ac.addRule();
        int i =1;
        for (WaterContainer wt : filteredByProvince) {
            ac.addRow(wt.getId(), wt.getStationName(), wt.getContainerName());
            ac.addRule();
            if(i%10==0) {
                ac.addRow(null,null,"Wyswietlic wiecej rekordow? n-koniec");
                ac.addRule();
                ac.getContext().setWidth(70);
                System.out.println(ac.render());
                          String choice = scanner.nextLine();
                               if(choice.equals("n")) getIDMenu(province);
            }
            i++;
        }


        getIDMenu(province);
    }

    public static void getIDMenu(String province) {
        AsciiTable getIDM = new AsciiTable();
        getIDM.addRule();
        getIDM.addRow("Podaj ID rzeki ", "3:Cofnij", "0:Wyjście").setTextAlignment(TextAlignment.CENTER);;
        getIDM.addRule();
        getIDM.getContext().setWidth(70);
        System.out.println(getIDM.render());
        try {
            int choiceID = Integer.valueOf(scanner.nextLine());
            if(filterFiles.getWaterContainerByID(choiceID)==null && choiceID!=3 && choiceID!=0) throw new IdLengthException();
            if (filterFiles.getWaterContainerByID(choiceID) != null) {
                showNewestData(choiceID);
            }
            if (choiceID == 3) {
                selectionMenu(province);
            }
            if (choiceID == 0) {
                closeApp();
            }
        } catch (NumberFormatException e) {
            System.out.println("Wprowadziłeś złą wartość");
            System.out.println("Nacisnij enter aby kontynuowac");
            scanner.nextLine();
            getIDMenu(province);
        }catch (IdLengthException e) {
        System.out.println("Podałeś nieprawidłowe ID");
            System.out.println("Nacisnij enter aby kontynuowac");
            scanner.nextLine();
        getIDMenu(province);
    }
    }

    public static void chooseContainerWithName(String province) {
        AsciiTable ccwID = new AsciiTable();
        AsciiTable ccwID2 = new AsciiTable();
        try {
            ccwID.addRule();
            ccwID.addRow("Podaj nazwę rzeki", "3:Cofnij", "0:Wyjście","Enter:wyświetl wszystkie").setTextAlignment(TextAlignment.CENTER);;
            ccwID.addRule();
            ccwID.getContext().setWidth(70);
            System.out.println(ccwID.render());
            String choice = scanner.nextLine();

            if (!(choice.equals("3")) && !(choice.equals("0"))) {


                List<WaterContainer> containers = filterFiles.filterThroughContainer(choice, province);
                for (WaterContainer wt : containers) {
                    ccwID2.addRule();
                    ccwID2.addRow(wt.getId(), wt.getProvince(), wt.getStationName(), wt.getContainerName());
                }
            }

            if (choice.equals("3")) {
                selectionMenu(province);
            } else if (choice.equals("0")) {
                closeApp();
            }
            ccwID2.addRule();
            ccwID2.getContext().setWidth(70);
            System.out.println(ccwID2.render());
            getIDMenu(province);
        } catch (ArithmeticException e ) {
            System.out.println("Nie znaleziono zbiornika");
            chooseContainerWithName(province);
        }
    }

    public static void showNewestData(int id) {
       List<History> sorted =  sortHistory(filterFiles.getWaterContainerByID(id));
        int lastIndexOfHistory =
                sorted.size()-1;
        //jakiś przypał z datą, pokazuje ostatni dzień lipca.
        AsciiTable sNd = new AsciiTable();
        sNd.addRule();
        sNd.addRow("WOJEWÓDZTWO", "NAZWA RZEKI", "NAZWA STACJI", "DATA", "STAN WODY [cm]", "PRZEPŁYW [m3/s]", "TEMPERATURA [℃]");
        sNd.addRule();
        String waterDeeps = doubleFormat.format(filterFiles.getWaterContainerByID(id).getHistory().get(lastIndexOfHistory).getWaterDeep());
        double waterDeep = Double.parseDouble(waterDeeps);
        String flowS = doubleFormat.format(filterFiles.getWaterContainerByID(id).getHistory().get(lastIndexOfHistory).getFlow());
        double flow = Double.parseDouble(flowS);
        String temperatureS = doubleFormat.format(filterFiles.getWaterContainerByID(id).getHistory().get(lastIndexOfHistory).getTemperature());
        double temperature = Double.parseDouble(temperatureS);
        String date = filterFiles.getWaterContainerByID(id).getHistory().get(lastIndexOfHistory).getDate().format(dateFormat);
        sNd.addRow(
                filterFiles.getWaterContainerByID(id).getProvince(),
                filterFiles.getWaterContainerByID(id).getStationName(),
                filterFiles.getWaterContainerByID(id).getContainerName(),
                date,
                waterDeep,
                flow,
                temperature);
        sNd.addRule();
        sNd.addRule();
        sNd.addRow(null, null, null, "Jeśli chcesz przejrzeć wszystkie historyczne dane ", null, " wybierz: 1", "3 : cofnij").setTextAlignment(TextAlignment.CENTER);;
        sNd.addRule();
        sNd.addRow(null, null, null, "Jeśli chcesz wyświetlić minimalne i maksymalne wartości dla podanej stacji ", null, " wybierz: 2", "0 : wyjście").setTextAlignment(TextAlignment.CENTER);;
        sNd.addRule();
        sNd.getContext().setWidth(150);
        System.out.println(sNd.render());

        int choiceID = Integer.valueOf(scanner.nextLine());
        switch (choiceID) {
            case 1: {
                showHistoricData(id);
            }
            case 2: {
                minMaxSelectMenu(id);
            }
            case 3: {
                getIDMenu(String.valueOf(province));
            }
            case 0: {
                closeApp();
            }
            default:
                System.out.println("Wybrałeś zły znak. Spróbuj jeszcze raz.");
                showNewestData(id);
        }
    }

    public static void showHistoricData(int id) {
        WaterContainer wt = filterFiles.getWaterContainerByID(id);
        AsciiTable sHd = new AsciiTable();
        sHd.addRule();
        sHd.addRow(null, "WOJEWÓDZTWO: " + wt.getProvince(), "RZEKA: " + wt.getStationName(), "STACJA: " + wt.getContainerName());
        sHd.addRule();
        sHd.addRow("DATA", "STAN WODY [cm]", "PRZEPŁYW [m3/s]", "TEMPERATURA [℃]");
        sHd.addRule();

        List<History> historyList = sortHistory(wt);
        for (int i = 0; i < historyList.size(); ) {
            for (int j = 0; j < 10; j++, i++) {
                if (i > historyList.size() - 1) {
                    break;
                }
                History hs = historyList.get(i);
                String date = hs.getDate().format(dateFormat);
                String waterDeepS = doubleFormat.format(hs.getWaterDeep());
                String flowS = doubleFormat.format(hs.getFlow());
                String temperatureS = doubleFormat.format(hs.getTemperature());
                double waterDeep = Double.parseDouble(waterDeepS);
                double flow = Double.parseDouble(flowS);
                double temperature = Double.parseDouble(temperatureS);

                sHd.addRow(date, waterDeep, flow, temperature);
                sHd.addRule();

            }
            System.out.println(sHd.render());
            if (i >= historyList.size()) {
                break;
            }
            if (!moreDataQ()) {
                break;
            }
        }


    }

    private static List<History> sortHistory(WaterContainer wt) {
        return wt.getHistory().stream().sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate())).collect(Collectors.toList());
    }

    public static boolean moreDataQ() {
        System.out.println("Czy chcesz więcej danych t/n");
        String more = scanner.nextLine();

        if (more.equals("n")) {
            closeApp();
            return false;
        } else if (more.equals("t")) {
            return true;
        } else {
            return moreDataQ();
        }
    }

    public static void minMaxSelectMenu(int id) {
        AsciiTable sHd = new AsciiTable();
// TU CHYBA  NIE TAK:
        sHd.addRule();
        sHd.addRow( "Wyświetl dane minimalne i maksymalne dla całego dostępnego zakresu", "Wybierz: 1").setTextAlignment(TextAlignment.CENTER);
        sHd.addRule();
        sHd.addRow( "Wyświetl dane minimalne i maksymalne dla wybranego przez siebie okresu", "Wybierz: 2").setTextAlignment(TextAlignment.CENTER);
        sHd.addRule();
        sHd.addRow( "3: cofnij",  "0: wyjdź").setTextAlignment(TextAlignment.RIGHT);
        sHd.addRule();
        System.out.println(sHd.render());
        int choice4 = Integer.valueOf(scanner.nextLine());
        if (choice4 == 1) {
            showMinMax(id);
        } else if (choice4 == 2) {
      // try {
           //   sHd.addRule();

           // sHd.addRow("Podaj datę początkową");
           String start = scanner.nextLine();
           //   sHd.addRule();
           //     sHd.addRow("Podaj datę końcową");
           String end = scanner.nextLine();
           //   sHd.render();

           showMinMaxforDatas(id, start, end);
       //}
        } else if (choice4 == 3) {
            showNewestData(id);
        } else if (choice4 == 0) {
            closeApp();
        } else {
            System.out.println("Wybrałeś zły znak. Spróbuj jeszcze raz.");
            minMaxSelectMenu(id);
        }
    }

    public static void showMinMax(int id) {
        AsciiTable sHd = new AsciiTable();
        sHd.addRule();
        sHd.addRow("Maksimum", "Minimum");
        sHd.addRule();
        sHd.addRow(filterFiles.minAndMaxValueOfHistoryFiles(id));
        sHd.addRule();
        filterFiles.minAndMaxValueOfHistoryFiles(id)
                .forEach(history -> {
                    sHd.addRow(history.getDate(),history.getWaterDeep());
                    sHd.addRule();
        });
        System.out.println(sHd.render());
    }

    public static void showMinMaxforDatas(int id, String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        filterFiles.minAndMaxValueOfHistoryFiles(id, startDate, endDate).forEach(history -> System.out.println(history.getDate()));
        filterFiles.minAndMaxValueOfHistoryFiles(id, startDate, endDate).forEach(history -> System.out.println(history.getWaterDeep()));

    }

    public static void main(String[] args) {
        Locale defaultLocale = new Locale("en", "US");
        Locale.setDefault(defaultLocale);
        getProperties();
        province = csvLoader.getProvince().stream().collect(Collectors.toList());

        createMenu();
    }


}