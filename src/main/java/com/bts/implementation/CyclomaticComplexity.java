package com.bts.implementation;

import java.io.*;
import java.util.*;

public class CyclomaticComplexity {
    public int check() {
        int complexity = 0;
        String fileName;
        String[] keywords = {"if","else","while","case","for","switch","do","continue","break","&&","||","?",":","catch","finally","throw","throws","default","return"};
        String words = "";
        String line = null;
        try {
            fileName = "src/main/resources/file.txt";
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null)
            {
                StringTokenizer stTokenizer = new StringTokenizer(line);
                while (stTokenizer.hasMoreTokens())
                {
                    words = stTokenizer.nextToken();
                    for(int i=0; i<keywords.length; i++)
                    {
                        if (keywords[i].equals(words))
                        {
                            complexity++;
                        }
                    }
                }
                line = br.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (complexity);
    }

    public void showCyclomaticComplexity(int ccValue) {
        System.out.println("\n -> Kompleksiteti Ciklomatik është "+ ccValue + " dhe minimumi " + ccValue + " test cases nevojiten për ta testuar!");
        System.out.print(" -> Rezultati : ");
        if (ccValue> 50){
            System.out.print("Metodë shumë komplekse dhe jostabile \n");
        }
        else if(ccValue>= 21 && ccValue<=50)
            System.out.print("Rrezikshmëri e lartë \n");
        else if(ccValue>= 11 && ccValue<=20)
            System.out.print("Rrezikshmëri mesatare \n");
        else
            System.out.print("Program me rrezikshmëri të ulët \n ");
    }

}
