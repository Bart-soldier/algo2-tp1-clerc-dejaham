package com.company;

import java.util.ArrayList;

public class Main {

    private static boolean isSatisfiable(String filename) {
        Reader reader = new Reader(filename); // STEP 1 : Read text file
        ImplicationGraph implicationGraph = new ImplicationGraph(reader); // Create implication graph
        //System.out.println(implicationGraph.getGraph().toString()); // Print to test

        DepthFirstSearch dps = new DepthFirstSearch(implicationGraph.getGraph()); // STEP 2 : Initialize DFS
        dps.search(); // Use DFS in implication graph
        ArrayList<Integer> result = dps.getResult(); // Get result from DFS
        //System.out.print(dps.toString()); // Print to test

        implicationGraph.transposeGraph(); // STEP 3 : Transpose Graph
        //System.out.println(implicationGraph.getGraph().toString()); // Print to test

        KosarajuAlgorithm ka = new KosarajuAlgorithm(implicationGraph.getGraph(), result); // STEP 4 : Initialize Kosaraju Algorithm
        ka.search(); // Use KA in transposed implication graph
        //System.out.print(ka.toString()); // Print to test

        return ka.isSatisfiable();
    }

    public static void main(String[] args) {
        //String filename = "formule-2-sat.txt"; // Formule satisfiable
        String filename = "formule-2-sat2.txt"; // Formule insatisfiable

        if(isSatisfiable(filename)) {
            System.out.println("La formule est satisifable !\n");
        }
        else {
            System.out.println("La formule est insatisifable...\n");
        }
    }
}
