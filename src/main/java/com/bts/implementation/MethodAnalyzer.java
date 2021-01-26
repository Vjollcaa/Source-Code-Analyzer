package com.bts.implementation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodAnalyzer {

    List<String> parameters = new ArrayList<>();
    String fileName;
    String line = null;

    public void analyzeMethod() throws IOException {

        fileName = "src/main/resources/file.txt";
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        try {
            line = br.readLine();
            while (line.contains("public") || line.contains("private")) {
                System.out.println("\n\n" + line);
                Pattern pattern = Pattern.compile("(?<=\\().*?(?=\\))");
                Matcher m = pattern.matcher(line);
                while(m.find()) {
                    StringTokenizer stTokenizer = new StringTokenizer(m.group(), ",");
                    while (stTokenizer.hasMoreTokens()) {
                        parameters.add(stTokenizer.nextToken());
                    }
                    System.out.println(parameters);
                }
                checkOutput(line);
                line = br.readLine();
            }
            checkParameters(parameters);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }

    public void checkParameters(List<String> parameters) {

        for (String parameter : parameters) {

            String inputVariableName = getLastToken(parameter);

            if (parameter.contains("int []") || parameter.contains("String []")) {
                System.out.println("\n -> Metoda nen testim ka si input nje variabel te tipit VARG, dhe fillimisht ajo mund te testohet me vlerat "+ inputVariableName +"={} " +
                        "apo "+ inputVariableName +"≠{}");
            } else if (parameter.contains("int")) {
                System.out.println("\n -> Metoda nen testim ka si input nje variabel te tipit INTEGER, dhe fillimisht ajo mund te testohet me vlerat "+ inputVariableName +"=0 " +
                        "apo "+ inputVariableName +">0");
            } else if (parameter.contains("string")) {
                System.out.println("\n -> Metoda nen testim ka si input nje variabel te tipit STRING, dhe fillimisht ajo mund te testohet me vlerat "+ inputVariableName +"={} " +
                        "apo "+ inputVariableName +"≠{}");
            } else if (parameter.contains("boolean")) {
                System.out.println("\n -> Metoda nen testim ka si input nje variabel te tipit BOOLEAN, dhe fillimisht ajo mund te testohet me vlerat "+ inputVariableName +"=true " +
                        "apo "+ inputVariableName +"=false");
            }
        }
    }

    public void checkOutput(String line){

        String outputVariableName = getFirstToken(line);

        if (outputVariableName.contains("int []") || outputVariableName.contains("String []")) {
            System.out.println("\n -> Metoda nën testim kthen një output të tipit varg.");
        } else if (outputVariableName.contains("int")) {
            System.out.println("\n -> Metoda nën testim kthen një output të tipit integer.");
        } else if (outputVariableName.contains("string")) {
            System.out.println("\n -> Metoda nën testim kthen një output të tipit string.");
        } else if (outputVariableName.contains("boolean")) {
            System.out.println("\n -> Metoda nën testim kthen një output të tipit boolean.");
        }
    }

    private String getLastToken(String strValue) {
        String[] strArray = strValue.split(" ");
        return strArray[strArray.length -1];
    }

    private String getFirstToken(String strValue) {
        return strValue.split(" ")[1];
    }
}
