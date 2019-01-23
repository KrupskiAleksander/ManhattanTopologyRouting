package Elka.Soib.Network;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Digraph graph = Digraph.generateManhattanDigraph(4);
        Integer squareWidth =15;
        Integer numberOfGraphTops = squareWidth*squareWidth;

        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(numberOfGraphTops, squareWidth);

        edgeWeightedDigraph.createSOIBGraphXd();

        FloydWarshall floydWarshall = new FloydWarshall();

        floydWarshall.findShortestPaths(edgeWeightedDigraph);
        floydWarshall.findLongestPaths(edgeWeightedDigraph);

//        Packet packet = new Packet(23,22);
          Packet.timeToLeave=3;
//        packet.generateRoute(floydWarshall);
        List<Packet> pakiety =  new ArrayList<>();
        for (int i = 0; i <200; i++){
            pakiety.add(new Packet(i+1,i+2));
            pakiety.get(i).generateRoute(floydWarshall);
            pakiety.get(i).buffers = new Buffer(squareWidth * squareWidth);
        }

        Buffer.bufferSize = 1;

        for(Packet pakiet : pakiety){
            pakiet.send();
        }

        for (int i = 0 ; i< 200; i++ ){
            for(Packet pakiet : pakiety){
                pakiet.traverse();
            }
        }
        int a =1;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        for(Packet pakiet : pakiety){
            if(pakiet.getStatus() == PacketStatus.SENT)
                b++;
            if(pakiet.getStatus() == PacketStatus.EXPIRED)
                c++;
            if(pakiet.getStatus() == PacketStatus.LOST)
                d++;
            if(pakiet.getStatus() == PacketStatus.NEW)
                e++;
            if(pakiet.getStatus() == PacketStatus.RECEIVED)
                f++;
        }
        System.out.println("SENT: "+ b + "EXPIRED: "+ c+"LOST: "+ d+"NEW: "+ e+ "RECEIVED: "+ f);
    }
}
