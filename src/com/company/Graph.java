package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<Label> {

    private class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
    }

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;


    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) {
        incidency.get(source).addLast(new Edge(source,dest,label));
    }

    public String toString() {
        String result = ("");
        result = result.concat(cardinal + "\n");
        for (int i = 0; i<cardinal;i++) {
            for (Edge e : incidency.get(i)) {
                result = result.concat(e.source + " " + e.destination + " "
                        + e.label.toString() + "\n");
            }
        }
        return result;
    }

    /**
     * Cette méthode vérifie que 'source' possède des arcs sortants dans le graph. Si c'est le cas, nous mettons toutes
     * les destinations possibles dans une LinkedList.
     *
     * @param source Sommet dont on veut connaître les voisins vers lequels nous pouvons nous déplacer.
     * @return La liste des voisins vers lequels nous pouvons nous déplacer.
     */
    public LinkedList getDestination(int source) {
        if(!(incidency.get(source).peek() == null)){
            LinkedList<Integer> destinations = new LinkedList<>();
            for(Edge edge : incidency.get(source)){
                destinations.add(edge.destination);
            }
            return destinations;
        }
        return null;
    }
}
