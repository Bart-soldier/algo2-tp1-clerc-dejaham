package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Reader reader = new Reader("formule-2-sat.txt"); // Read text file
        ImplicationGraph implicationGraph = new ImplicationGraph(reader); // Create implication graph
        System.out.println(implicationGraph.getGraph().toString()); // Print to test

        DepthFirstSearch dps = new DepthFirstSearch(implicationGraph.getGraph()); // Initialize DFS
        dps.search(); // Use DFS in implication graph
        System.out.print(dps.toString()); // Print to test
        ArrayList<Integer> result = dps.getResult(); // Get result from DFS

        implicationGraph.transposeGraph(); // Transpose Graph
        System.out.println(implicationGraph.getGraph().toString()); // Print to test

        KosarajuAlgorithm ka = new KosarajuAlgorithm(implicationGraph.getGraph(), result); // Initialize Kosaraju Algorithm
        ka.search(); // Use KA in transposed implication graph
        System.out.print(ka.toString()); // Print to test
    }
}
