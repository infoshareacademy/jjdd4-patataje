package com.hydrozagadka;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// Tu piszemy kodzik Misiaczki
        try {
            com.hydrozagadka.LoadFile ld = new com.hydrozagadka.LoadFile();
            ld.load();
            ld.readExample();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
