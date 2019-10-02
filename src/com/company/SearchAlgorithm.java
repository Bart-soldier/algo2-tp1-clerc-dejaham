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


    public SearchAlgorithm(Graph graph) {
        this.graph = graph;
        completedNodes = new ArrayList<>();
        unfinishedNodes = new Stack<>();
        discoveredNodes = new ArrayList<>(graph.order());
        initializeDiscoveredNodes();
        time = 1;
    }

    public ArrayList depthFirstSearch(){

        while (discoveredNodes.contains(false)){
            checkUnfinishedNodes();

            neighborsOfNode = graph.getDestination(unfinishedNodes.peek().nodeNumber);
            boolean indicator = true;

            checkNeighborsOfNode(indicator);
        }

        return completedNodes;
    }

    private void initializeDiscoveredNodes() {
        for(int i = 0; i < graph.order(); i++){
            discoveredNodes.add(false);
        }
    }

    private void checkUnfinishedNodes() {
        if(unfinishedNodes.empty()){
            unfinishedNodes.push(new SearchData(discoveredNodes.indexOf(false), time, -1));
            discoveredNodes.set(discoveredNodes.indexOf(false),true);
            time++;
        }
    }

    private void checkNeighborsOfNode(boolean indicator) {
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
}
