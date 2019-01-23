package Elka.Soib.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

        //Digraph graph = Digraph.generateManhattanDigraph(4);
        Integer squareWidth =15;
        int packetRatio = 20;
        int timeOfSimulation = 100;
        Integer numberOfGraphTops = squareWidth*squareWidth;

        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(numberOfGraphTops, squareWidth);

        edgeWeightedDigraph.createSOIBGraphXd();

        FloydWarshall floydWarshall = new FloydWarshall();

        floydWarshall.findShortestPaths(edgeWeightedDigraph);
        floydWarshall.findLongestPaths(edgeWeightedDigraph);

//        Packet packet = new Packet(23,22);
          Packet.timeToLeave=20;
          Packet.buffers = new Buffer(squareWidth * squareWidth);
          Buffer.bufferSize = 6;
//        packet.generateRoute(floydWarshall);





        List<Packet> pakiety =  new ArrayList<>();
        for (int i = 0; i <timeOfSimulation; i++){
            for (int j = 0; j <packetRatio; j++) {
                int random1 = ThreadLocalRandom.current().nextInt(0, (squareWidth * squareWidth));
                int random2 = ThreadLocalRandom.current().nextInt(0, (squareWidth * squareWidth));
                while  (random2 == random1){
                    random2 = ThreadLocalRandom.current().nextInt(0, (squareWidth * squareWidth));
                }
                Packet pakiecik = new Packet(random1, random2);

                pakiecik.generateRoute(floydWarshall);
                pakiecik.send();
                pakiety.add(pakiecik);


            }
            for(Packet pakiet : pakiety){
                pakiet.traverse();
            }

        }


//        for (int i = 0; i <200; i++){
//            pakiety.add(new Packet(i+1,i+2));
//            pakiety.get(i).generateRoute(floydWarshall);
//        }
//
//        Buffer.bufferSize = 1;
//
//        for(Packet pakiet : pakiety){
//            pakiet.send();
//        }
//
//        for (int i = 0 ; i< 200; i++ ){
//            for(Packet pakiet : pakiety){
//                pakiet.traverse();
//            }
//            pakiety.add(new Packet(i+1,i+2));
//            pakiety.get(pakiety.size() - 1).generateRoute(floydWarshall);
//            pakiety.get(pakiety.size() - 1).buffers = new Buffer(squareWidth * squareWidth);
//            pakiety.get(pakiety.size() - 1).send();
//
//        }
        int a =1;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        int delay = 0;
        double mean = 0;
        for(Packet pakiet : pakiety){
            if(pakiet.getStatus() == PacketStatus.SENT)
                b++;
            if(pakiet.getStatus() == PacketStatus.EXPIRED)
                c++;
            if(pakiet.getStatus() == PacketStatus.LOST)
                d++;
            if(pakiet.getStatus() == PacketStatus.NEW)
                e++;
            if(pakiet.getStatus() == PacketStatus.RECEIVED) {
                f++;
                delay = delay + pakiet.delay;
            }
        }
        mean = delay/f;
        double sumOfVariations= 0;
        double sumOfHops = 0;
        for(Packet pakiet : pakiety){
            pakiet.variation = (pakiet.delay-mean)*(pakiet.delay-mean);
            sumOfVariations = sumOfVariations + pakiet.variation;
            sumOfHops = sumOfHops + pakiet.getHops();
        }
        double variation = sumOfVariations/f;
        double hops = sumOfHops/f;

        System.out.println("SENT: " + b + "EXPIRED: " + c+"LOST: " + d+"NEW: " + e+ "RECEIVED: " + f+ " sum of delays" + delay+ " wariancja" + variation + " mean hops"  + hops);
    }
}
