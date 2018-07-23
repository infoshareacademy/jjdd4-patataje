package com.hydrozagadka;


import com.hydrozagadka.exceptions.IdLengthException;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Province {
    private static Logger logger = LoggerFactory.getLogger(Province.class);
    private static List<String> province;
    private static CSVLoader csvLoader = new CSVLoader();
    private static FilterFiles filterFiles = new FilterFiles(csvLoader);
    private static Scanner scanner = new Scanner(System.in);
    private static DecimalFormat doubleFormat;
    private static DateTimeFormatter dateFormat;
    private static String ANSI_COLOR = null;

    private static void getProperties() {
        Properties prop = new Properties();

        try {
            InputStream input = new FileInputStream("data/config.properties");
            prop.load(input);
            doubleFormat = new DecimalFormat(prop.getProperty("doubleformat"));
            dateFormat = DateTimeFormatter.ofPattern(prop.getProperty("dateformat"));
            ANSI_COLOR = prop.getProperty("purple");
            input.close();
        } catch (IOException ex) {
            System.out.println("Błąd przy wczytywaniu pliku konfiguracyjnego");
            logger.warn("Nieprawidłowy plik konfiguracyjny");
        }

    }

    private static void closeApp() {

        System.out.println("Aplikacja została zamknięta.");
        byeScreen();
        System.exit(0);
        logger.info("Aplikacja została zamknięta");
    }

    private static void createMenu() {

        AsciiTable at = new AsciiTable();
        System.out.println(ANSI_COLOR + "\n\nAPLIKACJA WYŚWIETLAJĄCA STAN WÓD DLA POLSKICH RZEK\n");
        welcomeScreen();

        System.out.println("Aby wyświetlić najświeższe dostępne dane:");
        try {
            at.addRule();
            at.addRow(null, null, null, "Wybierz województwo:").setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            for (int i = 0, j = 1; i < province.size() - 4; i += 4, j += 4) {
                at.addRow(j + ": " + province.get(i), (j + 1) + ": " + province.get(i + 1),
                        (j + 2) + ": " + province.get(i + 2), (j + 3) + ": " + province.get(i + 3));
                at.addRule();
            }
            at.addRow(null, null, null, "0: Wyjście").setTextAlignment(TextAlignment.RIGHT);
            at.addRule();
            at.getContext().setWidth(100);
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Blad podczas wczytywania danych program zostanie zamkniety");
            logger.error("Aplikacja została zamknięta");
            closeApp();

        }
    }

    private static void selectionMenu(String province) {
        AsciiTable secmenu = new AsciiTable();
        secmenu.addRule();
        secmenu.addRow("1:Wyświetl Stacje", "2:Wybierz Zbiornik", "3:Cofnij", "0:Wyjście").setTextAlignment(TextAlignment.CENTER);
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
                    logger.warn("Nieprawidłowe dane");
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Podaj liczbe!");
            selectionMenu(province);
        }
    }

    private static void showStationsforProvinces(String province) {
        AsciiTable ac = new AsciiTable();
        List<WaterContainer> filteredByProvince = filterFiles.showWaterContainersThroughProvince(province);
        ac.addRule();
        ac.addRow("ID", "NAZWA ZBIORNIKA", "NAZWA STACJI").setTextAlignment(TextAlignment.CENTER);
        ac.addRule();
        int i = 1;
        for (WaterContainer wt : filteredByProvince) {
            ac.addRow(wt.getId(), wt.getStationName(), wt.getContainerName());
            ac.addRule();
            if (i % 10 == 0) {
                ac.addRow(null, null, "Wyswietlic wiecej rekordow? n-koniec");
                ac.addRule();
                ac.getContext().setWidth(70);
                System.out.println(ac.render());
                String choice = scanner.nextLine();
                if (choice.equals("n")) getIDMenu(province);
            }
            i++;
        }
        getIDMenu(province);
    }

    private static void getIDMenu(String province) {
        AsciiTable getIDM = new AsciiTable();
        getIDM.addRule();
        getIDM.addRow("Podaj ID zbiornika ", "3:Cofnij", "0:Wyjście").setTextAlignment(TextAlignment.CENTER);
        getIDM.addRule();
        getIDM.getContext().setWidth(70);
        System.out.println(getIDM.render());
        try {
            Long choiceID = Long.valueOf(scanner.nextLine());
            if (filterFiles.getWaterContainerByID(choiceID) == null && choiceID != 3 && choiceID != 0)
                throw new IdLengthException();
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
        } catch (IdLengthException e) {
            System.out.println("Podałeś nieprawidłowe ID");
            System.out.println("Nacisnij enter aby kontynuowac");
            scanner.nextLine();
            logger.warn("Nieprawidłowe dane");
            getIDMenu(province);
        }
    }

    private static void chooseContainerWithName(String province) {
        AsciiTable ccwID = new AsciiTable();
        AsciiTable ccwID2 = new AsciiTable();
        try {
            ccwID.addRule();
            ccwID.addRow("Podaj nazwę zbiornika", "3:Cofnij", "0:Wyjście", "Enter:wyświetl wszystkie").setTextAlignment(TextAlignment.CENTER);
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
        } catch (ArithmeticException e) {
            System.out.println("Nie znaleziono zbiornika");
            chooseContainerWithName(province);
            logger.warn("Nieprawidłowe dane");
        }
    }

    private static void showNewestData(Long id) {
        List<History> sorted = sortHistory(filterFiles.getWaterContainerByID(id));
        legend();
        AsciiTable sNd = new AsciiTable();
        sNd.addRule();
        sNd.addRow("WOJEWÓDZTWO", "NAZWA ZBIORNIKA", "NAZWA STACJI", "DATA", "STAN WODY [cm]", "PRZEPŁYW [m3/s]", "TEMPERATURA [st. C]");
        sNd.addRule();
        double waterDeep = Double.parseDouble(doubleFormat.format(sorted.get(0).getWaterDeep()));
        double flow = Double.parseDouble(doubleFormat.format(sorted.get(0).getFlow()));
        double temperature = Double.parseDouble(doubleFormat.format(sorted.get(0).getTemperature()));
        String date = sorted.get(0).getDate().format(dateFormat);
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
        sNd.addRow(null, null, null, "Jeśli chcesz przejrzeć wszystkie historyczne dane ", null, " wybierz: 1", "3 : cofnij").setTextAlignment(TextAlignment.CENTER);
        sNd.addRule();
        sNd.addRow(null, null, null, "Jeśli chcesz wyświetlić minimalne i maksymalne wartości dla podanej stacji ", null, " wybierz: 2", "0 : wyjście").setTextAlignment(TextAlignment.CENTER);
        sNd.addRule();
        sNd.getContext().setWidth(130);
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
                logger.warn("Nieprawidłowe dane");
        }
    }

    private static void showHistoricData(Long id) {
        WaterContainer wt = filterFiles.getWaterContainerByID(id);
        AsciiTable sHd = new AsciiTable();
        legend();
        sHd.addRule();
        sHd.addRow(null, "WOJEWÓDZTWO: " + wt.getProvince(), "ZBIORNIK WODNY: " + wt.getStationName(), "STACJA: " + wt.getContainerName());
        sHd.addRule();
        sHd.addRow("DATA", "STAN WODY [cm]", "PRZEPŁYW [m3/s]", "TEMPERATURA [st. C]");
        sHd.addRule();

        List<History> historyList = sortHistory(wt);
        for (int i = 1; i < historyList.size(); ) {
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
        return wt.getHistory().stream()
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .collect(Collectors.toList());
    }

    private static boolean moreDataQ() {
        System.out.println("Czy chcesz więcej danych t/n");
        String more = scanner.nextLine();

        switch (more) {
            case "n":
                createMenu();
                return false;
            case "t":
                return true;
            default:
                return moreDataQ();
        }
    }

    private static void minMaxSelectMenu(Long id) {
        AsciiTable sHd = new AsciiTable();
        sHd.addRule();
        sHd.addRow("Wyświetl dane minimalne i maksymalne dla całego dostępnego zakresu", "Wybierz: 1").setTextAlignment(TextAlignment.CENTER);
        sHd.addRule();
        sHd.addRow("Wyświetl dane minimalne i maksymalne dla wybranego przez siebie okresu", "Wybierz: 2").setTextAlignment(TextAlignment.CENTER);
        sHd.addRule();
        sHd.addRow("3: cofnij", "0: wyjdź").setTextAlignment(TextAlignment.RIGHT);
        sHd.addRule();
        System.out.println(sHd.render());
        int choice4 = Integer.valueOf(scanner.nextLine());
        if (choice4 == 1) {
            showMinMax(id);
        } else if (choice4 == 2) {

            System.out.println("Podaj datę początkową (yyyy-mm-dd)");
            String start = scanner.nextLine();

            System.out.println("Podaj datę końcową (yyyy-mm-dd)");
            String end = scanner.nextLine();


            showMinMaxforDatas(id, start, end);
        } else if (choice4 == 3) {
            showNewestData(id);
        } else if (choice4 == 0) {
            closeApp();
        } else {
            System.out.println("Wybrałeś zły znak. Spróbuj jeszcze raz.");
            minMaxSelectMenu(id);
            logger.warn("Nieprawidłowe dane");
        }
    }

    private static void showMinMax(Long id) {
        AsciiTable sHd = new AsciiTable();
        sHd.addRule();
        sHd.addRow("Wartość Maksymalna", filterFiles.minAndMaxValueOfHistoryWaterDeeps(id).get(0).getDate() + " " + filterFiles.minAndMaxValueOfHistoryWaterDeeps(id).get(0).getWaterDeep());
        sHd.addRule();
        sHd.addRow("Wartość Minimalna", filterFiles.minAndMaxValueOfHistoryWaterDeeps(id).get(1).getDate() + " " + filterFiles.minAndMaxValueOfHistoryWaterDeeps(id).get(1).getWaterDeep());
        sHd.addRule();
        System.out.println(sHd.render());
    }

    private static void showMinMaxforDatas(Long id, String start, String end) {
        AsciiTable sMM = new AsciiTable();
        sMM.addRule();
        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            sMM.addRow("Wartość Maksymalna", filterFiles.minAndMaxValueOfHistoryWaterDeeps(id, startDate, endDate).get(0).getDate() + " " + filterFiles.minAndMaxValueOfHistoryWaterDeeps(id, startDate, endDate).get(0).getWaterDeep());
            sMM.addRule();
            sMM.addRow("Wartość Minimalna", filterFiles.minAndMaxValueOfHistoryWaterDeeps(id, startDate, endDate).get(1).getDate() + " " + filterFiles.minAndMaxValueOfHistoryWaterDeeps(id, startDate, endDate).get(1).getWaterDeep());
            sMM.addRule();
            System.out.println(sMM.render());

        } catch (DateTimeParseException e) {
            System.out.println("Podałeś nieprawidłową datę ");
            System.out.println("Nacisnij enter aby kontynuowac");
            scanner.nextLine();
            logger.warn("Nieprawidłowe dane");
            minMaxSelectMenu(id);
        }

        closeApp();
    }

    public static void main(String[] args) {
        Locale defaultLocale = new Locale("en", "US");
        Locale.setDefault(defaultLocale);
        getProperties();
        province = csvLoader.getProvince().stream().collect(Collectors.toList());

        createMenu();
    }

    private static void legend() {
        AsciiTable leg = new AsciiTable();
        leg.addRule();
        leg.addRow("LEGENDA").setTextAlignment(TextAlignment.CENTER);
        leg.addRule();
        leg.addRow("Stan wody 9999 lub 0.0 oznacza brak danych w bazie.");
        leg.addRule();
        leg.addRow("Przepływ 99999.999 lub 0.0 oznacza, że przepływ w tym dniu nie był opracowywany.");
        leg.addRule();
        leg.addRow("Temperatura 99.9 lub 0.0 oznacza brak danych w bazie, która może wynikać np. z braku pomiarów temperatury na stacji.");
        leg.addRule();
        leg.getContext().setWidth(40);
        System.out.println(leg.render());
        System.out.println("");
    }

    private static void welcomeScreen() {
        System.out.println("\n" +
                "\n" +
                "    o   o                                Witaj\n" +
                "                  /^^^^^7             W Aplikacji\n" +
                "    '  '     ,oO))))))))Oo,         Wykonanej przez\n" +
                "           ,'))))))))))))))), /{     PATATAJE team\n" +
                "      '  ,'o  ))))))))))))))))={   \n" +
                "         >    ))))))))))))))))={  Miłego korzystania!\n" +
                "         `,   ))))))\\ \\)))))))={    \n" +
                "           ',))))))))\\/)))))' \\{         ⓒJPPL\n" +
                "             '*O))))))))O*'\n");
        System.out.println("");
        logger.info("Aplikacja została uruchomiona");
    }

    private static void byeScreen() {
        logger.info("Aplikacja została zamknięta");
        System.out.println("\n" +
                "                 ,__\n" +
                "                   |  `'.\n" +
                "__           |`-._/_.:---`-.._\n" +
                "\\='.       _/..--'`__         `'-._\n" +
                " \\- '-.--\"`      ===        /   o  `',      Szkoda,\n" +
                "  )= (                 .--_ |       _.'     że nas\n" +
                " /_=.'-._             {=_-_ |   .--`-.    opuszczasz.\n" +
                "/_.'    `\\`'-._        '-=   \\    _.'    \n" +
                "    jgs  )  _.-'`'-..       _..-'`         żegnaj!\n" +
                "        /_.'        `/\";';`|\n" +
                "                     \\` .'/\n" +
                "                      '--'\n");
    }
}