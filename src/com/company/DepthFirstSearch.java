package com.company;

import java.util.ArrayList;

public class DepthFirstSearch extends SearchAlgorithm {

    public DepthFirstSearch(Graph graph) {
        super(graph);
    }

    /**
     * Dans cette méthode, l'algorithme de recherche est depth-first search (DPS).
     */
    @Override
    public void search(){
        while (discoveredNodes.contains(false) || !(unfinishedNodes.empty())){
            // Nous restons dans cette boucle tant que tous les sommets ne sont pas marqués comme terminés et que la pile n'est pas vide.
            checkUnfinishedNodes();

            neighborsOfNode = graph.getDestination(unfinishedNodes.peek().nodeNumber);

            checkNeighborsOfNode(completedNodes);
        }
        copyResult();
    }

    /**
     * Dans cette méthode, on choisit le sommet non découvert nommé par l'entier le plus petit.
     */
    @Override
    protected void checkUnfinishedNodes() {
        if(unfinishedNodes.empty()){
            unfinishedNodes.push(new SearchData(discoveredNodes.indexOf(false), time, -1));
            discoveredNodes.set(discoveredNodes.indexOf(false),true);
            time++;
        }
    }

    private void copyResult() {
        for(int i = 0; i < completedNodes.size(); i++) {
            result.add(completedNodes.get(i).nodeNumber);
        }
    }

    public ArrayList<Integer> getResult() {return result; }
}
