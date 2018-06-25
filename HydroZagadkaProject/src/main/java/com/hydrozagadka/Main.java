package com.hydrozagadka;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
	// Tu piszemy kodzik Misiaczki
        try {
            LoadFile ld = new LoadFile();
            FilterFiles filterFiles = new FilterFiles(ld.load());
           // ld.readExample();
            LocalDate localDate1 = LocalDate.of(2016,7,22);
            LocalDate localDate2 = LocalDate.of(2016,7,30);
            System.out.println(filterFiles.minAndMaxValueOfHistoryFiles(149180300, localDate1, localDate2));
       //     filterFiles.showWaterContainersThroughProvince("lubuskie").forEach(System.out::println);
       //     filterFiles.filterThroughContainer("Odra (1)").forEach(System.out::println);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
