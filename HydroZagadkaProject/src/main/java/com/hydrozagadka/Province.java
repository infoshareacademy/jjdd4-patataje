package com.hydrozagadka;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Province {
    public static void main(String[] args) {
        Map<Integer, String> province = new TreeMap<>();
        province.put(1, " Dolnośląskie");
        System.out.println("1 = Dolnośląskie");
        province.put(2, " Kujawsko-pomorskie");
        System.out.println("2 = Kujawsko-pomorskie");
        province.put(3, " Lubelskie");
        System.out.println("3 = Lubelskie");
        province.put(4, " Lubuskie");
        System.out.println("4 = Lubuskie");
        province.put(5, " Łódzkie");
        System.out.println("5 = Łódzkie");
        province.put(6, " Małopolskie");
        System.out.println("6 = Małopolskie");
        province.put(7, " Mazowieckie");
        System.out.println("7 = Mazowieckie");
        province.put(8, " Opolskie");
        System.out.println("8 = Opolskie");
        province.put(9, " Podkarpackie");
        System.out.println("9 = Podkarpackie");
        province.put(10, " Podlaskie");
        System.out.println("10 = Podlaskie");
        province.put(11, " Pomorskie");
        System.out.println("11 = Pomorskie");
        province.put(12, " Śląskie");
        System.out.println("12 = Śląskie");
        province.put(13, " Świętokrzyskie");
        System.out.println("13 = Świętokrzyskie");
        province.put(14, " Warmińsko-mazurskie");
        System.out.println("14 = Warmińsko-mazurskie");
        province.put(15, " Wielkopolskie");
        System.out.println("15 = Wielkopolskie");
        province.put(16, " Zachodniopomorskie");
        System.out.println("16 = Zachodniopomorskie ");
        {
            System.out.println("Wybierz województwo podając odpowiednią liczbę");
            Scanner scanner = new Scanner(System.in);
            int value = 0;
            while (true) {

                try {
                    value = scanner.nextInt();

                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Podaj poprawny numer województwa");
                    scanner.next();


                }

            }
            if (value > 17 || value < 0 || value == 0) {
                System.out.println("Podaj poprawny numer województwa");
                scanner.next();
            }


            System.out.println(province.get(value));


        }
    }

}