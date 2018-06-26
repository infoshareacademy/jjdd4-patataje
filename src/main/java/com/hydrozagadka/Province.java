package com.hydrozagadka;

import de.vandermeer.asciitable.AsciiTable;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Province {
    private static List<String> province;
    private static CSVLoader csvLoader = new CSVLoader();
    private static FilterFiles filterFiles = new FilterFiles(csvLoader);
    private static Scanner scanner = new Scanner(System.in);

    public static void closeApp() {

        System.out.println("Aplikacja została zamknięta.");
        System.exit(0);
    }

    public static void createMenu() throws InterruptedException {


        AsciiTable at = new AsciiTable();
        System.out.println("\n\nAPLIKACJA WYŚWIETLAJĄCA STAN WÓD DLA POLSKICH RZEK\n");
        at.addRule();
        at.addRow("Aby wyświetlić najświeższe dostępne dane");
        at.addRule();
        at.addRow("Wybierz województwo:");
        at.addRule();
        for (int i = 1; i < province.size(); i++) {
            at.addRow(i + ": " + province.get(i - 1));
            at.addRule();
        }
        at.addRow("0: Wyjście");
        at.addRule();
        at.getContext().setWidth(35);
        System.out.println(at.render());
        try {

            int choice = Integer.valueOf(scanner.nextLine());

            if (choice <= 16 && choice >= 1) {

                secondMenu(province.get(choice - 1));
            } else {
                System.out.println("Podaj poprawną liczbę województwa.");
                Thread.sleep(800);
                createMenu();
            }
        } catch (Exception e) {
            System.out.println("wybrales litere gnomie, sprobuj jeszcze raz");
            Thread.sleep(800);
            createMenu();
        }
    }

    public static void secondMenu(String province) throws IOException, InterruptedException {
        AsciiTable secmenu = new AsciiTable();
//Runtime.getRuntime().exec("cls");
        secmenu.addRule();
        secmenu.addRow("1:Wyświetl Stacje", "2:Wybierz Rzekę", "3:Cofnij", "0:Wyjście");
        secmenu.addRule();
        secmenu.getContext().setWidth(70);
        System.out.println(secmenu.render());
        try {
            int choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1: {
                    casemenu(province);
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
                    secondMenu(province);
                }
            }

        } catch (Exception e) {
            System.out.println("To nie cyferki, spróbuj jeszcze raz.!");
            secondMenu(province);
        }
    }

    public static void casemenu(String province) {
        AsciiTable ac = new AsciiTable();
        List<WaterContainer> filteredByProvince = filterFiles.showWaterContainersThroughProvince(province);
        ac.addRule();
        ac.addRow("ID", "NAZWA STACJI", "NAZWA RZEKI");
        ac.addRule();
        for (WaterContainer wt : filteredByProvince) {
            ac.addRow(wt.getId(), wt.getStationName(), wt.getContainerName());
            ac.addRule();
        }

        ac.getContext().setWidth(70);
        System.out.println(ac.render());
        getIDMenu(province);
    }

    public static void getIDMenu(String province) {
        AsciiTable getIDM = new AsciiTable();
        getIDM.addRule();
        getIDM.addRow("Podaj ID rzeki ", "3:Cofnij", "0:Wyjście");
        getIDM.addRule();
        getIDM.getContext().setWidth(70);
        System.out.println(getIDM.render());
        try {
            int choiceID = Integer.valueOf(scanner.nextLine());
            if (filterFiles.getWaterContainerByID(choiceID) != null) {
                showNewestData(choiceID);
            }
            if (choiceID == 3) {
                secondMenu(province);
            }
            if (choiceID == 0) {
                closeApp();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void chooseContainerWithName(String province) {
        AsciiTable ccwID = new AsciiTable();
        AsciiTable ccwID2 = new AsciiTable();
        ccwID.addRule();
        ccwID.addRow("Podaj nazwę rzeki", "3:Cofnij", "0:Wyjście");
        ccwID.addRule();
        ccwID.getContext().setWidth(70);
        System.out.println(ccwID.render());
        String choice = scanner.nextLine();
        List<WaterContainer> containers = filterFiles.filterThroughContainer(choice);
        for (WaterContainer wt : containers) {
            ccwID2.addRule();
            ccwID2.addRow(wt.getId(), wt.getProvince(), wt.getStationName(), wt.getContainerName());

        }
        ccwID2.addRule();
        ccwID2.getContext().setWidth(70);
        System.out.println(ccwID2.render());
        getIDMenu(province);

    }

    public static void showNewestData(int id) {
        int lastIndexOfHistory =
                filterFiles.getWaterContainerByID(id).getHistory().size() - 1;

        //jakiś przypał z datą, pokazuje ostatni dzień lipca.

        AsciiTable sNd = new AsciiTable();
        sNd.addRule();
        sNd.addRow("WOJEWÓDZTWO", "NAZWA RZEKI", "NAZWA STACJI", "DATA", "STAN WODY [cm]", "PRZEPŁYW [m3/s]", "TEMPERATURA [℃]");
        sNd.addRule();
        sNd.addRow(
                filterFiles.getWaterContainerByID(id).getProvince(),
                filterFiles.getWaterContainerByID(id).getStationName(),
                filterFiles.getWaterContainerByID(id).getContainerName(),
                filterFiles.getWaterContainerByID(id).getHistory().get(lastIndexOfHistory).getDate(),
                filterFiles.getWaterContainerByID(id).getHistory().get(lastIndexOfHistory).getWaterDeep(),
                filterFiles.getWaterContainerByID(id).getHistory().get(lastIndexOfHistory).getFlow(),
                filterFiles.getWaterContainerByID(id).getHistory().get(lastIndexOfHistory).getTemperature());
        sNd.addRule();
        sNd.addRow(null, null, null, null, "Czy chcesz zobaczyć wszystkie historyczne dane?", null, " Wybierz: T(Tak)/N(Nie)");
        sNd.addRule();
        System.out.println(sNd.render());

        String yOrN = scanner.nextLine();
        if (yOrN.equals("T")) {
            showHistoricData(id);
        } else if (yOrN.equals("N")) {
            System.out.println("Program został zamknięty.");
            System.exit(0);
        } else {
            System.out.println("Wybrałeś zły znak. Spróbuj jeszcze raz.");
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

        List<History> ciapek = wt.getHistory().stream().sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate())).collect(Collectors.toList());
        for (int i = 0; i < ciapek.size(); ) {
            for (int j = 0; j < 10; j++, i++) {
                if (i > ciapek.size() - 1) {
                    break;
                }
                History hs = ciapek.get(i);
                sHd.addRow(hs.getDate(), hs.getWaterDeep(), hs.getFlow(), hs.getTemperature());
                sHd.addRule();

            }
            System.out.println(sHd.render());
            if (i >= ciapek.size()) {
                break;
            }
            if (!pytanko()) {
                break;
            }
        }


    }

    public static boolean pytanko() {
        System.out.println("Czy chcesz więcej danych T/N");
        String more = scanner.nextLine();

        if (more.equals("N")) {
            System.out.println("Aplikacja się zamknęła,");
            return false;
        } else if (more.equals("T")) {
            return true;
        } else {
            return pytanko();
        }
    }

    public static void main(String[] args) {
        province = csvLoader.getProvince().stream().collect(Collectors.toList());
        try {
            createMenu();

        } catch (InterruptedException e) {
            System.out.println("Za długo czekałeś, oj oj...");
        }

    }


}
