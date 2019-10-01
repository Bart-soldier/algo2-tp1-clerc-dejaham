package com.company;

public class Main {

    public static void main(String[] args) {

        Reader reader = new Reader("formule-2-sat.txt");
        ImplicationGraph implicationGraph = new ImplicationGraph(reader);
        System.out.println(implicationGraph.getGraph().toString());
    }
}
