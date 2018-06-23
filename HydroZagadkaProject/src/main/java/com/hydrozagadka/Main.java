package com.hydrozagadka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static  BufferedReader br;

    public static void main(String[] args) throws IOException {
	// Tu piszemy kodzik Misiaczki
//        try {
//            LoadFile ld = new LoadFile();
//            FilterFiles filterFiles = new FilterFiles(ld.load());
//            ld.readExample();
//            System.out.println(filterFiles.minValueOfHistoryFiles());
//        } catch (FileNotFoundException e) {
//            System.out.println("Nie znaleziono pliku");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//    int a,b,d;
//        a=10;
//        b=10;
//        d=2013;
//        Date date = new GregorianCalendar(d, b-1, a).getTime();
//
//       br = new BufferedReader(new StringReader("abcd\nbcde\ncdef"));
//        String lin;
//        String rev = "";
//
//        while ((lin = br.readLine()) != null){
//            char[] g = lin.toCharArray();
//            for (int i = g.length-1; i >=0 ; i--) {
//                rev+=g[i];
//            }
// //           System.out.print(rev);
//            rev+="\n";

        String a,b;
        a="avb";
        b="baaa";
        System.out.println(a.compareTo(b));
        }

    }

