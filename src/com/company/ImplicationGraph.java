package com.company;

import java.awt.*;

public class ImplicationGraph {

    private Graph graph;

    public ImplicationGraph(Reader fileDataReader){
        graph = new Graph((Integer) fileDataReader.getFileData().get(0) * 2);
//TODO

        for(int i = 1; i < graph.order(); i = i+2){
            int litteral1 = (Integer) fileDataReader.getFileData().get(i);
            litteral1 = conversion(litteral1, graph.order());
            int litteral2 = (Integer) fileDataReader.getFileData().get(i+1);
            litteral2 = conversion(litteral2, graph.order());

            int negationLitteral1 = negation(litteral1, graph.order());
            int negationLitteral2 = negation(litteral2, graph.order());

            graph.addArc(negationLitteral1, litteral2, new Label(""));
            graph.addArc(negationLitteral2, litteral1, new Label(""));
        }
    }

    public Graph getGraph() {
        return graph;
    }

    /**
     * Cette fonction converti un littéral négatif en un entier positif afin qu'il puisse être utilisé avec la classe Graph.
     * Pour ce faire, nous avons décidé que les littéraux précédemment négatif suivrait les littéraux positif dans le graph.
     * Exemple : Si un graph a n variables, la conversion d'un littéral négatif est égale à la valeur absolue de ce littéral + n.
     * Ainsi, au lieu d'avoir un tableau qui s'étend de -, à n, nous avons un tableau qui s'étend de 0 à 2 * n - 1.
     *
     * @param litteral Litteral à convertir.
     * @param size Nombre de sommets dans le graph.
     * @return Le littéral transformer.
     */
    public int conversion (int litteral, int size) {
        if (litteral < 0) {
            litteral = Math.abs(litteral) + size/2;
        }
        return litteral - 1;
    }

    public int negation (int litteral, int size) {
        if (litteral < size/2) {
            return litteral + size/2;
        }
        return litteral - size/2;
    }
}
