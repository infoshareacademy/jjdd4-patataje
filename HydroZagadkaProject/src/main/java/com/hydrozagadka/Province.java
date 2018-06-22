package com.hydrozagadka;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Province {
    public static void main(String[] args) throws IOException {
        LoadFile lf = new LoadFile();
        FilterFiles filterFiles = new FilterFiles(lf.load());
        Map<Integer, String> province = new TreeMap<>();
        province.put(1,  "dolnośląskie");
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
        int value = 0;
        while (true) {
            try {
                value = scanner.nextInt();
                if (value > 16 || value < 0) {
                    System.out.println("Podaj poprawny numer województwa");
                } else {

                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawny numer województwa");
            }
        }
        System.out.println(province.get(value));

//        filterFiles.showWaterContainers(province.get(value));
    }

}