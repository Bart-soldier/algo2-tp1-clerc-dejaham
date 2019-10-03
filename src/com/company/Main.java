package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Reader reader = new Reader("formule-2-sat.txt");
        ImplicationGraph implicationGraph = new ImplicationGraph(reader);
        System.out.println(implicationGraph.getGraph().toString());

        SearchAlgorithm dps = new SearchAlgorithm(implicationGraph.getGraph());
        dps.depthFirstSearch();
        System.out.print(dps.toString());

        implicationGraph.transposeGraph(reader);
        System.out.println(implicationGraph.getGraph().toString());
        dps = new SearchAlgorithm(implicationGraph.getGraph());
    }
}
