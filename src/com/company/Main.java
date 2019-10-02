package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Reader reader = new Reader("formule-2-sat.txt");
        ImplicationGraph implicationGraph = new ImplicationGraph(reader);
        System.out.println(implicationGraph.getGraph().toString());

        ArrayList<SearchData> list = new ArrayList<>();
        SearchAlgorithm dps = new SearchAlgorithm(implicationGraph.getGraph());
        list = dps.depthFirstSearch();
    }
}
