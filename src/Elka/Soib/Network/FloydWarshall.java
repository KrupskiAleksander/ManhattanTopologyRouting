package Elka.Soib.Network;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshall {


    private Double[][] matrixOfDistances;
    private String[][] matrixOfRoutes;

    public void findShortestPaths(EdgeWeightedDigraph edgeWeightedDigraph) {

        int sizeOfGraph = edgeWeightedDigraph.getV();
        int numberOfPaths = edgeWeightedDigraph.getE();

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
            routeDiscription[v][w] = threw;
        }

        for (int k = 0; k < sizeOfGraph; k++) {
            for (int i = 0; i < sizeOfGraph; i++) {
                for (int j = 0; j < sizeOfGraph; j++) {
                    if(distance[i][j] > distance[i][k] + distance[k][j]) {
                        String threw = routeDiscription[i][k] + ":" + routeDiscription[k][j];
                        distance[i][j] = distance[i][k] + distance[k][j];

                        routeDiscription[i][j] = threw;
                    }
                }
            }

        }

        matrixOfDistances = distance;
        matrixOfRoutes = routeDiscription;
    }
}
