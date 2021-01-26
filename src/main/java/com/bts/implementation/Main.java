package com.bts.implementation;

import java.io.IOException;

public class Main {

    public static void main(String ss[]) throws IOException {
        CyclomaticComplexity cc = new CyclomaticComplexity();
        cc.showCyclomaticComplexity(cc.check());

        MethodAnalyzer ma = new MethodAnalyzer();
        ma.analyzeMethod();
    }
}
