package com.bts.implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnalyzesMethods {

    public static void main(String [] args) throws FileNotFoundException {
        // Skeneri per lexues te inputit

        Scanner scanner = new Scanner(new File("src/main/resources/file.txt"));
        scanner.useDelimiter("[\r\n]");
        String pattern = ".*public.*";
        String javaBook = null;

        while (scanner.hasNext()) {
            if (scanner.hasNext(pattern)) {
                javaBook = scanner.next(pattern);
                System.out.println(javaBook);
            } else {
                scanner.next();
            }
        }
        scanner.close();

        assert javaBook != null;
        Scanner scanner2 = new Scanner(javaBook);
        String parameters = scanner2.findInLine("(?<=\\().*?(?=\\))");
        System.out.println(parameters);

        if(parameters.contains("int []")){
            System.out.println("Metoda nen testim ka si input nje variabel te tipit varg, dhe fillimisht ajo mund te testohet me vlerat input={} apo input≠{}");
        }

        scanner2.close();

        //String metoda = cka skenohet si tekst
        //double res1=kompleksiteti(metoda);
        //analizohen variablat hyrese te metodes

        System.out.println("Per metoden e insertuar nevoiten minimum+ res1 + test case" +
                "dhe sygjerohet te testohet metoda me vlerat testuese si: +variablatHyrese(metoda)...");
    }

    private static void info(String javaBook) {
    }

    public double kompleksiteti (String metoda){
        //kalulohet kompksiteti ciklomatik i metodes
        return 0.0;
    }
    public String variablatHyrese(String metoda){
        //lexohet rrshti i pare i stringut metoda
        // parsohet pjesa e variablave
        //for each variabel ne metode
        // parsohet tipi i variables dhe ne baze te tipit vendosni vlerat min dhe medium


        return "";
    }
}
