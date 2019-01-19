package Elka.Soib.Network;

public class Main {

    public static void main(String[] args) {

        //Digraph graph = Digraph.generateManhattanDigraph(4);
        Integer squareWidth = 5;
        Integer numberOfGraphTops = squareWidth*squareWidth;

        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(numberOfGraphTops, squareWidth);

        edgeWeightedDigraph.createSOIBGraphXd();

        FloydWarshall floydWarshall = new FloydWarshall();

        floydWarshall.findShortestPaths(edgeWeightedDigraph);

        Packet packet = new Packet(23,22);

        packet.setTimeToLeave(2);

        packet.generateRoute(floydWarshall);

        packet.send();

        for (int i = 0 ; i< 20; i++ ){
            packet.traverse();
        }



    }
}
