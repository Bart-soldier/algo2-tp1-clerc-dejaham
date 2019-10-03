package com.company;

import java.util.ArrayList;

public class KosarajuAlgorithm extends SearchAlgorithm {

    private int resultIndex;

    public KosarajuAlgorithm(Graph graph, ArrayList<Integer> result) {
        super(graph);
        this.result = result;
        resultIndex = result.size() - 1;
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
            unfinishedNodes.push(new SearchData(result.get(resultIndex), time, -1));
            discoveredNodes.set(result.get(resultIndex), true);
            time++;
            resultIndex--;
            completedNodes.add(new SearchData(-1, -1, -1));
            // Les composantes fortement connexes sont délimités par des -1
        }
    }

    private boolean isNegation (int litteral1, int litteral2, int size) {
        if ((litteral1 < size/2) && (litteral1 == litteral1 + size/2)) {
            return true;
        }
        else if (litteral1 == litteral2 - size/2) {
            return true;
        }
        return false;
    }

    public boolean isSatisfiable() {
        int i; // for-loop index
        int j; // while-loop index
        for(i = 0; i < completedNodes.size(); i++) { // Parcours la liste de résultats
            if(completedNodes.get(i).nodeNumber != -1) { // Si nous sommes au sein d'un CFC
                j = i + 1;
                if(j < completedNodes.size()) { // Nous vérifions qu'il n'est pas le dernière élément de la liste
                    while (completedNodes.get(j).nodeNumber != -1) { // Nous regardons tous les éléments de la composantes
                        if (isNegation(completedNodes.get(i).nodeNumber, completedNodes.get(j).nodeNumber, result.size()))
                            return false;
                        // Si un élément de la composante est la négation d'un autre élément de cette même composante,
                        // Alors la formule est insatisfiable
                        j++;
                    }
                }
            }
        }
        return true; // Sinon la formule est satisfiable
    }

    public String toString() {
        String string = ("Strongly Connected Components :\n");
        for(int i = 0; i < completedNodes.size(); i++) {
            if(completedNodes.get(i).nodeNumber != -1) {
                string += completedNodes.get(i).nodeNumber + " ";
            }
            else {
                string += "\n";
            }

        }
        return string + "\n";
    }
}
