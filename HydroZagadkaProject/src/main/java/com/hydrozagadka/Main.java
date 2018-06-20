package com.hydrozagadka;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// Tu piszemy kodzik Misiaczki
        try {
            LoadFile ld = new LoadFile();
            FilterFiles filterFiles = new FilterFiles(ld.load());
            System.out.println(filterFiles.minValueOfHistoryFiles());
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
