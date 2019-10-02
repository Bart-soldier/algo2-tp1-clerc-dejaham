package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstSearch {

    private ArrayList<OrderDPS> completedNodes;

    private Graph graph;

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
        completedNodes = new ArrayList<>();
    }

    public ArrayList search(){

        Stack<OrderDPS> stack = new Stack<>();
        ArrayList<Boolean> discovered = new ArrayList<>(graph.order());
        LinkedList<Integer> destinations;
        int time = 1;

        for(int i = 0; i < graph.order(); i++){
            discovered.add(false);
        }

        while (discovered.contains(false)){
            if(stack.empty()){
                stack.push(new OrderDPS(discovered.indexOf(false), time, -1));
                discovered.set(discovered.indexOf(false),true);
                time++;
            }
            destinations = graph.getDestination(stack.peek().nodeNumber);

            boolean bool = true;

            if (!(destinations == null)) {
                int i = 0;
                while (bool && i < destinations.size()){
                    if (!discovered.get(destinations.get(i))) {
                        stack.push(new OrderDPS(destinations.get(i), time, -1));
                        discovered.set(destinations.get(i),true);
                        time++;
                        bool = false;
                    }
                }
            }

            if((destinations == null) || (bool)) {

            }

        }
    }

}
