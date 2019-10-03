package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class SearchAlgorithm {

    private Graph graph;
    private ArrayList<SearchData> completedNodes;
    private Stack<SearchData> unfinishedNodes;
    private ArrayList<Boolean> discoveredNodes;
    private LinkedList<Integer> neighborsOfNode;
    int time;

    /**
     * La classe SearchData est utilisée en tant qu'objet ayant trois attribus qui seront utiles à DPS.
     */
    private class SearchData {

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
        this.graph = graph; // Le graph a parcourir.
        completedNodes = new ArrayList<>(); // Les sommets du graph qui ont étés découverts et sont terminés.
        unfinishedNodes = new Stack<>(); // Les sommets du graph qui ont seulement étés découverts ou sont les prochains à être découverts.
        discoveredNodes = new ArrayList<>(graph.order()); // Une liste de boolean contenant les sommets du graphs.
                                                          // La valeur est true si le sommet est découvert, false sinon.
        initializeDiscoveredNodes();
        time = 1;
    }

    /**
     * Cette méthode représente l'implémentation de l'algorithme de recherche depth-first search (DPS).
     *
     * @return Un ArrayList contenant tous les sommets du graph (avec leurs attribus SearchData) dans l'ordre croissant de finishTime.
     */
    public void depthFirstSearch(){

        while (discoveredNodes.contains(false) || !(unfinishedNodes.empty())){ // Nous restons dans cette boucle tant que tous les sommets ne sont pas marqués comme terminés.
            checkUnfinishedNodes();

            neighborsOfNode = graph.getDestination(unfinishedNodes.peek().nodeNumber);

            checkNeighborsOfNode();
        }
    }

    /**
     * Cette méthode initialize toutes les valeurs de discoveredNodes à false.
     */
    private void initializeDiscoveredNodes() {
        for(int i = 0; i < graph.order(); i++){
            discoveredNodes.add(false);
        }
    }

    /**
     * Cette méthode vérifie si la pile unfinishedNodes est vide. Si elle l'est, elle rajoute un sommet qui n'est pas encore
     * marqué comme découvert et le marque comme tel.
     */
    private void checkUnfinishedNodes() {
        if(unfinishedNodes.empty()){
            unfinishedNodes.push(new SearchData(discoveredNodes.indexOf(false), time, -1));
            discoveredNodes.set(discoveredNodes.indexOf(false),true);
            time++;
        }
    }

    /**
     *  Si le sommet actuel possèdes des destinations possibles, nous prenons la première qio n'a pas été découverte et
     * nous l'ajoutons à la pile des sommets découverts ou à découvrir 'unfinishedNodes'.
     *  Si le sommet actuel n'a aucun arc sortant ou que ceux-ci amène vers des sommets déjà visités, alors ce sommet est
     * marqué comme terminé et est ajouté à la liste 'completedNodes'.
     */
    private void checkNeighborsOfNode() {
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
        String string = new String("Node number - Arrival time - Finish Time\n");
        for(int i = 0; i < completedNodes.size(); i++) {
            string = string.concat(completedNodes.get(i).nodeNumber + " - " + completedNodes.get(i).arrivalTime + " - "
                    + completedNodes.get(i).finishTime + "\n");
        }
        return string;
    }

    /*
    public void depthFirstSearchTransposeGraph() {

    }
     */
}
