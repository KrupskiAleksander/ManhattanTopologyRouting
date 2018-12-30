package Elka.Soib.Network;

public class Main {

    public static void main(String[] args) {

        //Digraph graph = Digraph.generateManhattanDigraph(4);
        Integer squareWidth = 4;
        Integer numberOfGraphTops = squareWidth*squareWidth;

        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(numberOfGraphTops, squareWidth);

        edgeWeightedDigraph.createSOIBGraphXd();

    }
}
