package com.bts.implementation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodAnalyzer {

    List<String> parameters = new ArrayList<>();
    String fileName ="src/main/resources/method_1.txt";
    String line = null;
    String inputVariableName = null;

    FileReader fr = new FileReader(fileName);
    BufferedReader br = new BufferedReader(fr);
    Pattern pattern = Pattern.compile("(?<=\\().*?(?=\\))");

    public MethodAnalyzer() throws FileNotFoundException {
    }

    public void analyzeMethod() throws IOException {

        try {
            line = br.readLine();
            while (line.contains("public") || line.contains("private")) {
                getParameters(line);
                checkOutput(line);
                line = br.readLine();
            }
            checkParameters(parameters);
            checkNumberOfConditions();
            analyzeConditions(inputVariableName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }

    private void getParameters(String line) {

        Matcher m = pattern.matcher(line);
        while(m.find()) {
            StringTokenizer stTokenizer = new StringTokenizer(m.group(), ",");
            while (stTokenizer.hasMoreTokens()) {
                parameters.add(stTokenizer.nextToken());
            }
        }
    }

    public void checkParameters(List<String> parameters) {

        if (parameters.isEmpty()) {
            System.out.println("\n -> Metoda nuk pranon asnjë input variabël!");
        } else {
            for (String parameter : parameters) {
                inputVariableName = getLastToken(parameter);

                if (parameter.contains("int []") || parameter.contains("String []")) {
                    System.out.println("\n -> Metoda nën testim ka si input një variabël të tipit VARG, dhe fillimisht ajo mund të testohet me vlerat " + inputVariableName + "=[] " +
                            "apo " + inputVariableName + "≠[]");
                } else if (parameter.contains("int")) {
                    System.out.println("\n -> Metoda nën testim ka si input një variabël të tipit INTEGER, dhe fillimisht ajo mund të testohet me vlerat " + inputVariableName + "=0 " +
                            "apo " + inputVariableName + ">0");
                } else if (parameter.contains("string") || parameter.contains("String")) {
                    System.out.println("\n -> Metoda nën testim ka si input një variabël të tipit STRING, dhe fillimisht ajo mund të testohet me vlerat " + inputVariableName + "=null " +
                            "apo " + inputVariableName + "≠null");
                } else if (parameter.contains("boolean") || parameter.contains("Boolean")) {
                    System.out.println("\n -> Metoda nen testim ka si input nje variabel te tipit BOOLEAN, dhe fillimisht ajo mund te testohet me vlerat " + inputVariableName + "=true " +
                            "apo " + inputVariableName + "=false");
                } else if (parameter.contains("List")) {
                    System.out.println("\n -> Metoda nen testim ka si input nje variabel te tipit LIST, dhe fillimisht ajo mund te testohet me vlerat " + inputVariableName + "={} " +
                            "apo " + inputVariableName + "≠{}");
                }
            }
        }
    }

    private String getLastToken(String strValue) {
        String[] strArray = strValue.split(" ");
        return strArray[strArray.length -1];
    }

    public void checkOutput(String line){

        String outputVariableName = getFirstToken(line);

        if(outputVariableName.equals("static")) {
            outputVariableName = getSecondToken(line);
        }

        if (outputVariableName.contains("int []") || outputVariableName.contains("String []")) {
            System.out.println("\n -> Metoda nën testim kthen një output të tipit VARG.");
        } else if (outputVariableName.contains("int")) {
            System.out.println("\n -> Metoda nën testim kthen një output të tipit INTEGER.");
        } else if (outputVariableName.contains("string") || outputVariableName.contains("String")) {
            System.out.println("\n -> Metoda nën testim kthen një output të tipit STRING.");
        } else if (outputVariableName.contains("boolean")) {
            System.out.println("\n -> Metoda nën testim kthen një output të tipit BOOLEAN.");
        }
    }

    private String getFirstToken(String strValue) {
        return strValue.split(" ")[1];
    }

    private String getSecondToken(String strValue) {
        return strValue.split(" ")[2];
    }

    public void checkNumberOfConditions() throws IOException {

        int conditionNr = 0;

        line = br.readLine();
        while(line != null){
            if (line.contains("for") || line.contains("if")) {
                conditionNr++;
            }
            line = br.readLine();
        }
        if (conditionNr != 0) {
            System.out.println("\n -> Nevojiten maximum " + conditionNr * 2 + " test cases! Por ky numër mund të optimizohet edhe më tej.");
        }
    }

    public void analyzeConditions(String inputVariable) throws IOException {

        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        List<String> forConditions = new ArrayList<>();
        List<String> ifConditions = new ArrayList<>();
        List<String> whileConditions = new ArrayList<>();

        try {
            line = br.readLine();
            while (line != null) {
                if (line.contains("for")) {
                    Matcher m = pattern.matcher(line);
                    while (m.find()) {
                        getForConditions(m, forConditions);
                    }
                } else if (line.contains("if")) {
                    Matcher m = pattern.matcher(line);
                    while (m.find()) {
                        getIfConditions(m, ifConditions);
                    }
                } else if (line.contains("while")) {
                    Matcher m = pattern.matcher(line);
                    while (m.find()) {
                        getWhileConditions(m, whileConditions);
                    }
                }
                line = br.readLine();
            }
            checkForConditions(forConditions, inputVariable);
            checkIfConditions(ifConditions, inputVariable);
            checkWhileConditions(whileConditions, inputVariable);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }

    private void getForConditions(Matcher m, List<String> forConditions) {

        StringTokenizer stTokenizer = new StringTokenizer(m.group(), ";");
        while (stTokenizer.hasMoreTokens()) {
            forConditions.add(stTokenizer.nextToken());
        }
    }

    private void getIfConditions(Matcher m, List<String> ifConditions) {

        StringTokenizer stTokenizer = new StringTokenizer(m.group());
        while (stTokenizer.hasMoreTokens()) {
            ifConditions.add(stTokenizer.nextToken());
        }
    }

    private void getWhileConditions(Matcher m, List<String> whileConditions) {

        StringTokenizer stTokenizer = new StringTokenizer(m.group());
        while (stTokenizer.hasMoreTokens()) {
            whileConditions.add(stTokenizer.nextToken());
        }
    }

    private void checkForConditions(List<String> forConditions, String inputVariable) {

            if (!forConditions.isEmpty() && forConditions.get(1).contains(inputVariable)) {
                System.out.println("\n -> Kushti FOR: "+ forConditions +" varet nga variabla hyrëse: " + inputVariable);
            } else if(forConditions.isEmpty()) {
                System.out.println("\n -> Nuk është detektuar asnjë kusht FOR!");
            } else {
                System.out.println("\n -> Kushti FOR: "+ forConditions +" NUK varet nga variabla hyrëse: " + inputVariable);
        }
    }

    private void checkIfConditions(List<String> ifConditions, String inputVariable){

        for (String ifCondition : ifConditions) {
            if (inputVariable !=null && ifCondition.contains(inputVariable)) {
                System.out.println("\n -> Kushti IF: "+ ifCondition +" varet nga variabla hyrëse: " + inputVariable);
            } else {
                System.out.println("\n -> Kushti IF: "+ ifCondition +" NUK varet nga variabla hyrëse: " + inputVariable);
            }
        }
    }

    private void checkWhileConditions(List<String> whileConditions, String inputVariable) {

        for (String whileCondition : whileConditions) {
            if (whileCondition.contains(inputVariable)) {
                System.out.println("\n -> Kushti WHILE: "+ whileCondition +" varet nga variabla hyrëse: " + inputVariable);
            } else {
                System.out.println("\n -> Kushti WHILE: "+ whileCondition +" NUK varet nga variabla hyrëse: " + inputVariable);
            }
        }
    }
}
