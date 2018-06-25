package com.hydrozagadka;


import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

public class Province {
    private static List<String> province;
    public static void createMenu(){
        for (int i = 1; i < province.size(); i++) {
            System.out.println(i+": "+province.get(i-1));

        }
    }

    public static void main(String[] args){
        CSVLoader csvLoader = new CSVLoader();
        FilterFiles filterFiles = new FilterFiles(csvLoader);
         province = csvLoader.getProvince().stream().collect(Collectors.toList());

        createMenu();
//        String value = "";
//        int choice = -1;
//        while (choice != 0) {
//            try {
//                value = scanner.next();
//                choice = Integer.parseInt(value);
//                if (choice > 16 || choice < 1) {
//                    if (choice != 0) System.out.println("Podaj poprawny numer województwa");
//                } else {
//                    int choice2;
//                    //second choice watercontainer or point clearing screen
//                    for (int i = 0; i < 5; i++) {
//                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
//                    }
//                    System.out.println("=======================================================");
//                    System.out.println("1: Wybierz Stacje  | 2: Wybierz Zbiornik | 0: Wyjscie ");
//                    System.out.println("======================================================");
//                    choice2 = Integer.parseInt(scanner.next());
//                    if (choice2 == 1) {
                  //      List<WaterContainer> filteredByState = filterFiles.getWaterContainers(province.get(choice));
               //         filteredByState.sort(Comparator.comparing(WaterContainer::getContainerName));
               //         for (int i = 0; i < filteredByState.size(); i++) {
                //            System.out.println(filteredByState.get(i).getContainerName() + "\t  " + filteredByState.get(i).getId());

                  //      }
//                        System.out.println("\n\n");
//                        System.out.println("=======================================================");
//                        System.out.println(" WPISZ ID ZBIORNIKA   |     0 WYJSCIE");
//                        System.out.println("========================================================");
//                        choice = scanner.nextInt();
//                        for (int i = 0; i < 5; i++) {
//                            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
//                        }
//                        System.out.println("=======================================================");
//                        System.out.println(" 1. HISTORIA   |   2.HISTORYCZNE MIN MAX  |   0 WYJSCIE |");
//                        System.out.println("========================================================");
//               //         filterFiles.showNewestData(choice);
//                        System.out.println();
//                        int choice3 = scanner.nextInt();
//                        if (choice3 == 1) filterFiles.readExample(choice);
//                    }
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Podaj poprawny numer województwa");
//            } catch (NumberFormatException e) {
//                System.out.println("Podaj liczbe wojewodztwa!");
//            } catch (NullPointerException e){
//                System.out.println("Podales zle id!");
//            }
//        }
    }
}