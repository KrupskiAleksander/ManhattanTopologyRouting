package Elka.Soib.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

        System.out.println("Podaj rozmiar sieci: ");
        Scanner sc = new Scanner(System.in);
        Integer squareWidth = sc.nextInt();
       // Integer squareWidth =15;
        System.out.println("Srednią ilosc pakietow na sekunde: ");
        int packetRatio = sc.nextInt();;
        System.out.println("Podaj czas dzialania symulowanej sieci: ");
        int timeOfSimulation = sc.nextInt();
        System.out.println("Podaj czas zycia pakietow ");
        int ttl = sc.nextInt();
        System.out.println("Podaj rozmiar buforu ");
        int bbbbbb = sc.nextInt();
        System.out.println("Wybierz ruting: 1 - najkrotsza sciezka 2 - najdluzsza sciezka 3- losowy");
        int kind = sc.nextInt();
        Integer numberOfGraphTops = squareWidth*squareWidth;

        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(numberOfGraphTops, squareWidth);

        edgeWeightedDigraph.createSOIBGraphXd();

        FloydWarshall floydWarshall = new FloydWarshall();

        floydWarshall.findShortestPaths(edgeWeightedDigraph);
        floydWarshall.findLongestPaths(edgeWeightedDigraph);

          Packet.timeToLeave=ttl;
          Packet.buffers = new Buffer(squareWidth * squareWidth);
          Buffer.bufferSize = bbbbbb;





        List<Packet> pakiety =  new ArrayList<>();
        for (int i = 0; i <timeOfSimulation; i++){
            for (int j = 0; j <packetRatio; j++) {
                int random1 = ThreadLocalRandom.current().nextInt(0, (squareWidth * squareWidth));
                int random2 = ThreadLocalRandom.current().nextInt(0, (squareWidth * squareWidth));
                int random3 = ThreadLocalRandom.current().nextInt(0, (squareWidth * squareWidth));
                while  (random2 == random1){
                    random2 = ThreadLocalRandom.current().nextInt(0, (squareWidth * squareWidth));
                }
                while  (random3 == random1 || random3 == random2){
                    random3 = ThreadLocalRandom.current().nextInt(0, (squareWidth * squareWidth));
                }
                Packet pakiecik = new Packet(random1, random2, random3, kind);

                pakiecik.generateRoute(floydWarshall);
                pakiecik.send();
                pakiety.add(pakiecik);


            }
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
        double delay = 0;
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
            if(pakiet.getStatus() == PacketStatus.RECEIVED) {
                pakiet.variation = (pakiet.delay - mean ) * (pakiet.delay - mean);
                sumOfVariations = sumOfVariations + pakiet.variation;
                sumOfHops = sumOfHops + pakiet.getHops();
            }
        }
        double variation = sumOfVariations/f;
        double hops = sumOfHops/f;

        System.out.format("Pakiety wciąż w drodze: " + b + "\nPakiety przeterminowane (przekroczone TTL): " + c+"\nPakiety utracone: " + d + "\nPakiety odebrane prawidłowo: " + f+ "\nSrednie opóźnienie pakietu: %.2f%n" + "Wariancja: %.2f%n"+ "Srednia ilość przeskoków pakietu: %.2f%n",delay/f, variation,hops) ;
    }
}
