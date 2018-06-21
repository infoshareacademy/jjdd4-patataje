package com.hydrozagadka;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
	// Tu piszemy kodzik Misiaczki
        try {
            LoadFile ld = new LoadFile();
            FilterFiles filterFiles = new FilterFiles(ld.load());
            ld.readExample();
            System.out.println(filterFiles.minValueOfHistoryFiles());
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            e.printStackTrace();
        }

    int a,b,d;
        a=10;
        b=10;
        d=2013;
        Date date = new GregorianCalendar(d, b-1, a).getTime();
    }
}
