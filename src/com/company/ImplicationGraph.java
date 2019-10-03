package com.company;

import java.awt.*;

public class ImplicationGraph {

    private Graph graph;
    private Reader fileDataReader;

    public ImplicationGraph(Reader fileDataReader){
        this.fileDataReader = fileDataReader;
        graph = new Graph((Integer) fileDataReader.getFileData().get(0) * 2);

        for(int i = 1; i < fileDataReader.getFileData().size(); i = i+2){
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

    public void transposeGraph() {
        graph = new Graph((Integer) fileDataReader.getFileData().get(0) * 2);

        for (int i = 1; i < fileDataReader.getFileData().size(); i = i + 2) {
            int litteral1 = (Integer) fileDataReader.getFileData().get(i);
            litteral1 = conversion(litteral1, graph.order());
            int litteral2 = (Integer) fileDataReader.getFileData().get(i + 1);
            litteral2 = conversion(litteral2, graph.order());

            int negationLitteral1 = negation(litteral1, graph.order());
            int negationLitteral2 = negation(litteral2, graph.order());

            graph.addArc(litteral2, negationLitteral1, new Label(""));
            graph.addArc(litteral1, negationLitteral2, new Label(""));
        }
    }

    public Graph getGraph() {
        return graph;
    }

    /**
     * Cette fonction converti un littéral négatif en un entier positif afin qu'il puisse être utilisé avec la classe Graph.
     * Pour ce faire, nous avons décidé que les littéraux précédemment négatif suivrait les littéraux positif dans le graph.
     * Exemple : Si un graph a n variables, la conversion d'un littéral négatif est égale à la valeur absolue de ce littéral + n.
     * Ainsi, au lieu d'avoir un tableau qui s'étend de -n à n, nous avons un tableau qui s'étend de 0 à 2 * n - 1.
     *
     * @param litteral Litteral à convertir.
     * @param size Nombre de sommets dans le graph.
     * @return Le littéral transformer.
     */
    private int conversion (int litteral, int size) {
        if (litteral < 0) {
            litteral = Math.abs(litteral) + size/2;
        }
        return litteral - 1;
    }

    private int negation (int litteral, int size) {
        if (litteral < size/2) {
            return litteral + size/2;
        }
        return litteral - size/2;
    }

    /*
    public ImplicationGraph(Reader fileDataReader) {
        this.fileDataReader = fileDataReader;
        graph = new Graph((Integer) fileDataReader.getFileData().get(0) * 2);
        int i; // for-loop index
        int j; // while-loop index
        int litteral1, litteral2, negationLitteral1, negationLitteral2;

        for (i = 1; i < fileDataReader.getFileData().size(); i++) { // Parcours les entiers lus dans le ficher .txt
            if ((Integer) fileDataReader.getFileData().get(i) != 0) { // Si nous ne sommes pas à la fin d'une clause
                j = i + 1;
                if (j < fileDataReader.getFileData().size()) { // Nous vérifions qu'il n'est pas le dernière élément de la liste
                    while ((Integer) fileDataReader.getFileData().get(j) != 0) { // Nous regardons tous les éléments de la clause
                        litteral1 = (Integer) fileDataReader.getFileData().get(i);
                        litteral1 = conversion(litteral1, graph.order());
                        litteral2 = (Integer) fileDataReader.getFileData().get(j);
                        litteral2 = conversion(litteral2, graph.order());

                        negationLitteral1 = negation(litteral1, graph.order());
                        negationLitteral2 = negation(litteral2, graph.order());

                        graph.addArc(negationLitteral1, litteral2, new Label(""));
                        graph.addArc(negationLitteral2, litteral1, new Label(""));
                        // On crée des arcs entre tous les éléments qui font partis de la même clause
                        j++;
                    }
                }
            }
        }
    }
   */
}
