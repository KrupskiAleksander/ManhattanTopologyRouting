package Elka.Soib.Network;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedDigraph {
    private final int V;
    private final int squareWidth;
    private int E;
    private ArrayList<DirectedEdge> paths = new ArrayList<>();
    //private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V, int squareWidth) {
        this.V = V;
        this.E = E;
        this.squareWidth = squareWidth;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }


    public void createSOIBGraphXd() {
        Integer pathsCounter = 0;
        Integer numberOfTop = 1;
        Integer[][] tabOfTops = new Integer[squareWidth][squareWidth];
        for(Integer i = 0; i < squareWidth; i++){
            for(Integer j = 0; j < squareWidth; j++){
                tabOfTops[i][j] = numberOfTop;
                numberOfTop++;
            }
        }

        for(Integer i = 0; i < squareWidth; i++){
            for(Integer j = 0; j < squareWidth; j++){

                if(i%2 == 0 && (j+1) <= (squareWidth - 1)){
                    Integer from = tabOfTops[i][j];
                    Integer to = tabOfTops[i][j+1];
                    DirectedEdge directedEdge = new DirectedEdge(from, to, 1);
                    paths.add(directedEdge);
                    pathsCounter++;

                    System.out.println("From: " + from + " To: " + to);
                } else if ((j+1) <= (squareWidth - 1)) {
                    Integer from = tabOfTops[i][j+1];
                    Integer to = tabOfTops[i][j];
                    DirectedEdge directedEdge = new DirectedEdge(from, to, 1);
                    paths.add(directedEdge);
                    pathsCounter++;

                    System.out.println("From: " + from + " To: " + to);
                } else {
                    if(i%2 == 0){
                        Integer from = tabOfTops[i][squareWidth - 1];
                        Integer to = tabOfTops[i][0];
                        DirectedEdge directedEdge = new DirectedEdge(from, to, 1);
                        paths.add(directedEdge);
                        pathsCounter++;

                        System.out.println("From: " + from + " To: " + to);
                    } else {
                        Integer from = tabOfTops[i][0];
                        Integer to = tabOfTops[i][squareWidth - 1];
                        DirectedEdge directedEdge = new DirectedEdge(from, to, 1);
                        paths.add(directedEdge);
                        pathsCounter++;

                        System.out.println("From: " + from + " To: " + to);
                    }
                }

                if(j%2 == 0 && (i + 1) <= (squareWidth - 1)){
                    Integer from = tabOfTops[i+1][j];
                    Integer to = tabOfTops[i][j];
                    DirectedEdge directedEdge = new DirectedEdge(from, to, 1);
                    paths.add(directedEdge);
                    pathsCounter++;

                    System.out.println("From: " + from + " To: " + to);
                } else if ((i + 1) <= (squareWidth - 1)) {
                    Integer from = tabOfTops[i][j];
                    Integer to = tabOfTops[i + 1][j];
                    DirectedEdge directedEdge = new DirectedEdge(from, to, 1);
                    paths.add(directedEdge);
                    pathsCounter++;

                    System.out.println("From: " + from + " To: " + to);
                } else {
                    if(j%2 == 0){
                        Integer from = tabOfTops[0][j];
                        Integer to = tabOfTops[squareWidth - 1][j];
                        DirectedEdge directedEdge = new DirectedEdge(from, to, 1);
                        paths.add(directedEdge);
                        pathsCounter++;

                        System.out.println("From: " + from + " To: " + to);
                    } else {
                        Integer from = tabOfTops[squareWidth - 1][j];
                        Integer to = tabOfTops[0][j];
                        DirectedEdge directedEdge = new DirectedEdge(from, to, 1);
                        paths.add(directedEdge);
                        pathsCounter++;

                        System.out.println("From: " + from + " To: " + to);
                    }
                }
            }
        }
        System.out.println("Liczba krawedzi: " + pathsCounter);
    }
}
