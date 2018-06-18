package com.hydrozagadka;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// Tu piszemy kodzik Misiaczki
        try {
            LoadFile ld = new LoadFile();
            ld.load();
            ld.readExample();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
