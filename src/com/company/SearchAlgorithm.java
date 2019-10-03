package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

abstract class SearchAlgorithm {

    protected Graph graph; // Le graph a parcourir.
    protected ArrayList<Integer> result; // Le résultat de la recherche.
    protected ArrayList<SearchData> completedNodes; // Les sommets du graph qui ont étés découverts et sont terminés.
    protected Stack<SearchData> unfinishedNodes; // Les sommets du graph qui ont seulement étés découverts ou sont les prochains à être découverts.
    protected ArrayList<Boolean> discoveredNodes; // Une liste de boolean contenant les sommets du graphs.
                                                  // La valeur est true si le sommet est découvert, false sinon.
    protected LinkedList<Integer> neighborsOfNode;
    protected int time;

    /**
     * La classe SearchData est utilisée en tant qu'objet ayant trois attribus qui seront utiles à DPS.
     */
    protected static class SearchData {

        int nodeNumber;
        int arrivalTime;
        int finishTime;

        public SearchData(int nodeNumber, int arrivalTime, int finishTime) {
            this.nodeNumber = nodeNumber;
            this.arrivalTime = arrivalTime;
            this.finishTime = finishTime;
        }
    }


    public SearchAlgorithm(Graph graph) {
        this.graph = graph;
        result = new ArrayList<>();
        completedNodes = new ArrayList<>();
        unfinishedNodes = new Stack<>();
        discoveredNodes = new ArrayList<>(graph.order());
        initializeDiscoveredNodes();
        time = 1;
    }

    /**
     * Cette méthode représente l'implémentation de l'algorithme de recherche.
     */
    abstract void search();

    /**
     * Cette méthode vérifie si la pile unfinishedNodes est vide. Si elle l'est, elle rajoute un sommet qui n'est pas encore
     * marqué comme découvert et le marque comme tel.
     */
    abstract void checkUnfinishedNodes();

    /**
     * Cette méthode initialize toutes les valeurs de discoveredNodes à false.
     */
    protected void initializeDiscoveredNodes() {
        for(int i = 0; i < graph.order(); i++){
            discoveredNodes.add(false);
        }
    }

    /**
     *  Si le sommet actuel possèdes des destinations possibles, nous prenons la première qio n'a pas été découverte et
     * nous l'ajoutons à la pile des sommets découverts ou à découvrir 'unfinishedNodes'.
     *  Si le sommet actuel n'a aucun arc sortant ou que ceux-ci amène vers des sommets déjà visités, alors ce sommet est
     * marqué comme terminé et est ajouté à la liste 'completedNodes'.
     */
    protected void checkNeighborsOfNode(ArrayList<SearchData> completedNodes) {
        boolean indicator = true;

        if (!(neighborsOfNode == null)) {
            int i = 0;
            while (indicator && i < neighborsOfNode.size()){
                if (!discoveredNodes.get(neighborsOfNode.get(i))) {
                    unfinishedNodes.push(new SearchData(neighborsOfNode.get(i), time, -1));
                    discoveredNodes.set(neighborsOfNode.get(i),true);
                    time++;
                    indicator = false;
                }
                i++;
            }
        }

        if((neighborsOfNode == null) || (indicator)) {
            unfinishedNodes.lastElement().finishTime = time;
            completedNodes.add(unfinishedNodes.pop());
            time++;
        }
    }

    public String toString() {
        String string = ("Node number / Arrival time / Finish Time\n");
        for(int i = 0; i < completedNodes.size(); i++) {
            string = string.concat(completedNodes.get(i).nodeNumber + " / " + completedNodes.get(i).arrivalTime + " / "
                    + completedNodes.get(i).finishTime + "\n");
        }
        return string + "\n";
    }
}
