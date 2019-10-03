package com.company;

import java.util.ArrayList;

public class KosarajuAlgorithm extends SearchAlgorithm {

    public KosarajuAlgorithm(Graph graph, ArrayList<Integer> result) {
        super(graph);
        this.result = result;
    }

    /**
     * Dans cette méthode, l'algorithme de recherche est depth-first search (DPS).
     */
    @Override
    public void search(){
        while (discoveredNodes.contains(false) || !(unfinishedNodes.empty())){
            checkUnfinishedNodes();

            neighborsOfNode = graph.getDestination(unfinishedNodes.peek().nodeNumber);

            checkNeighborsOfNode(completedNodes);
        }
    }

    /**
     * Dans cette méthode, on choisit le sommet non découvert dont la date de fin de traitement du premier DFS est la plus grande.
     */
    @Override
    protected void checkUnfinishedNodes() {
        if(unfinishedNodes.empty()){
            int nextNodeNumber = result.get(result.size() - completedNodes.size() - 1);
            unfinishedNodes.push(new SearchData(nextNodeNumber, time, -1));
            discoveredNodes.set(nextNodeNumber, true);
            time++;
        }
    }
}
