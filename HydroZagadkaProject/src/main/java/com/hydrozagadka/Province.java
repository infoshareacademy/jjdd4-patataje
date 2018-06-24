package com.hydrozagadka;

import java.io.IOException;
import java.text.Collator;
import java.util.*;

public class Province {
    public static void main(String[] args) throws IOException {
        FilterFiles filterFiles = new FilterFiles(new LoadFile().load());
        Map<Integer, String> province = new TreeMap<>();
        province.put(1, "dolnośląskie");
        System.out.println("1 = Dolnośląskie");
        province.put(2, "kujawsko-pomorskie");
        System.out.println("2 = Kujawsko-pomorskie");
        province.put(3, "lubelskie");
        System.out.println("3 = Lubelskie");
        province.put(4, "lubuskie");
        System.out.println("4 = Lubuskie");
        province.put(5, "łódzkie");
        System.out.println("5 = Łódzkie");
        province.put(6, "małopolskie");
        System.out.println("6 = Małopolskie");
        province.put(7, "mazowieckie");
        System.out.println("7 = Mazowieckie");
        province.put(8, "opolskie");
        System.out.println("8 = Opolskie");
        province.put(9, "podkarpackie");
        System.out.println("9 = Podkarpackie");
        province.put(10, "podlaskie");
        System.out.println("10 = Podlaskie");
        province.put(11, "pomorskie");
        System.out.println("11 = Pomorskie");
        province.put(12, "śląskie");
        System.out.println("12 = Śląskie");
        province.put(13, "świętokrzyskie");
        System.out.println("13 = Świętokrzyskie");
        province.put(14, "warmińsko-mazurskie");
        System.out.println("14 = Warmińsko-mazurskie");
        province.put(15, "wielkopolskie");
        System.out.println("15 = Wielkopolskie");
        province.put(16, "zachodniopomorskie");
        System.out.println("16 = Zachodniopomorskie ");
        System.out.println("Jeżeli chesz wyjśc z aplikacji wciśnij 0");
        System.out.println("Wybierz województwo podając odpowiednią liczbę");
        Scanner scanner = new Scanner(System.in);
        String value = "";
        int choice = -1;
        while (choice != 0) {
            try {
                value = scanner.next();
                choice = Integer.parseInt(value);
                if (choice > 16 || choice < 1) {
                    if (choice != 0) System.out.println("Podaj poprawny numer województwa");
                } else {
                    int choice2;
                    //second choice watercontainer or point clearing screen
                    for (int i = 0; i < 5; i++) {
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
                    }
                    System.out.println("=======================================================");
                    System.out.println("1: Wybierz Stacje  | 2: Wybierz Zbiornik | 0: Wyjscie ");
                    System.out.println("======================================================");
                    choice2 = Integer.parseInt(scanner.next());
                    if (choice2 == 1) {
                        List<WaterContainer> filteredByState = filterFiles.getWaterContainers(province.get(choice));
                        filteredByState.sort(Comparator.comparing(WaterContainer::getContainerName));
                        for (int i = 0; i < filteredByState.size(); i++) {
                            System.out.println(filteredByState.get(i).getContainerName() + "\t  " + filteredByState.get(i).getId());

                        }
                        System.out.println("\n\n");
                        System.out.println("=======================================================");
                        System.out.println(" WPISZ ID ZBIORNIKA   |     0 WYJSCIE");
                        System.out.println("========================================================");
                        choice = scanner.nextInt();
                        for (int i = 0; i < 5; i++) {
                            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
                        }
                        System.out.println("=======================================================");
                        System.out.println(" 1. HISTORIA   |   2.HISTORYCZNE MIN MAX  |   0 WYJSCIE |");
                        System.out.println("========================================================");
                        filterFiles.showNewestData(choice);
                        System.out.println();
                        int choice3 = scanner.nextInt();
                        if (choice3 == 1) filterFiles.readExample(choice);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawny numer województwa");
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbe wojewodztwa!");
            }
        }
    }
}