package Elka.Soib.Network;

public class Main {

    public static void main(String[] args) {

        //Digraph graph = Digraph.generateManhattanDigraph(4);
        Integer squareWidth = 3;
        Integer numberOfGraphTops = squareWidth*squareWidth;

        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(numberOfGraphTops, squareWidth);

        edgeWeightedDigraph.createSOIBGraphXd();

        FloydWarshall floydWarshall = new FloydWarshall();

        floydWarshall.findShortestPaths(edgeWeightedDigraph);

    }
}
