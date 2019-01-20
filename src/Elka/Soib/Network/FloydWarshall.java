package Elka.Soib.Network;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshall {


    public Double[][] matrixOfDistances;
    public String[][] matrixOfRoutes;
    public ArrayList<DirectedEdge>[][] matrixWithListsOfPaths;

    public void findShortestPaths(EdgeWeightedDigraph edgeWeightedDigraph) {

        int sizeOfGraph = edgeWeightedDigraph.getV();
        int numberOfPaths = edgeWeightedDigraph.getE();

        ArrayList<DirectedEdge>[][] listOfPaths = new ArrayList[sizeOfGraph][sizeOfGraph];

        ArrayList<String> basicPaths = new ArrayList<>();

        Double[][] distance = new Double[sizeOfGraph][sizeOfGraph];

        for (int i = 0; i < sizeOfGraph; i++) {
            for (int j = 0; j < sizeOfGraph; j++) {
                if (i == j) {
                    distance[i][j] = Double.valueOf(0);
                } else {
                    distance[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        for(int iterator = 0; iterator < edgeWeightedDigraph.paths.size(); iterator++) {
            DirectedEdge directedEdge = edgeWeightedDigraph.paths.get(iterator);

            int from = directedEdge.from();
            int to = directedEdge.to();
            Double weigh = directedEdge.getWeight();

            basicPaths.add(Integer.toString(from) + "|" + Integer.toString(to));
            distance[from][to] = weigh;

        }

        String[][] routeDiscription = new String[sizeOfGraph][sizeOfGraph];

        for (int i = 0; i < numberOfPaths; i++) {
            int v = edgeWeightedDigraph.paths.get(i).getV();
            int w = edgeWeightedDigraph.paths.get(i).getW();
            String threw = Integer.toString(v) +"-"+ Integer.toString(w);

            DirectedEdge directedEdge = new DirectedEdge(v,w,1.0);

            listOfPaths[v][w] = new ArrayList<>();
            listOfPaths[v][w].add(directedEdge);

            routeDiscription[v][w] = threw;
        }

        for (int k = 0; k < sizeOfGraph; k++) {
            for (int i = 0; i < sizeOfGraph; i++) {
                for (int j = 0; j < sizeOfGraph; j++) {
                    if (i != j) {
                        if (distance[i][j] > distance[i][k] + distance[k][j]) {

                            ArrayList<DirectedEdge> pathItoK = new ArrayList<>();
                            ArrayList<DirectedEdge> pathKtoJ = new ArrayList<>();

                            for (int l = 0; l < listOfPaths[i][k].size(); l++) {
                                pathItoK.add(listOfPaths[i][k].get(l));
                            }
                            for (int l = 0; l < listOfPaths[k][j].size(); l++) {
                                pathKtoJ.add(listOfPaths[k][j].get(l));
                            }

                            for (int l = 0; l < pathKtoJ.size(); l++) {
                                //System.out.println(i + "," + j + "," + k);
                                pathItoK.add(pathKtoJ.get(l));

                            }
//
                            listOfPaths[i][j] = new ArrayList<>();
//
                            listOfPaths[i][j] = pathItoK;

                           // System.out.println("Route from: " + i + " to " + j + " threw: " + pathItoK.toString() + " and k = " + k);


                            String threw = routeDiscription[i][k] + ":" + routeDiscription[k][j];
                            distance[i][j] = distance[i][k] + distance[k][j];

                            routeDiscription[i][j] = threw;
                        }
                    }
                }
            }

        }

        matrixOfDistances = distance;
        matrixOfRoutes = routeDiscription;
        matrixWithListsOfPaths = listOfPaths;
    }
}
