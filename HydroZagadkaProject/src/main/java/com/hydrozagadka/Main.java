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
            LocalDate localDate1 = LocalDate.of(2016,7,12);
            LocalDate localDate2 = LocalDate.of(2016,7,17);
            System.out.println(filterFiles.minAndMaxValueOfHistoryFiles(149180020, localDate1, localDate2));
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
