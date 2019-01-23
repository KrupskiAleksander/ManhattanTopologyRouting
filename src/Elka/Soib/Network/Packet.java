package Elka.Soib.Network;

import java.util.ArrayList;
import java.util.List;

public class Packet {
    private int startNode;
    private int finalNode;
    public static int timeToLeave;
    private PacketStatus status;
    private int timestamp;
    public int delay;
    public double variation;
    public int hops;
    public int stoppedTime;
    private List<Integer> route;
    public static Buffer buffers;
    public int randomNumber;
    int kind;
    List<Integer> randomPath;
    List<Integer> pathLongest;


    public Packet(int startNode, int finalNode, int randomNumber, int kind)    {
        status = PacketStatus.NEW;
        timestamp = 1;
        delay = 0;
        variation = 0;
        stoppedTime = 0;
        this.kind = kind;
        this.randomNumber = randomNumber;
        this.startNode = startNode;
        this.finalNode = finalNode;
    }
    public void generateRoute(FloydWarshall floydWarshallResult){
        List<Integer> path = new ArrayList<Integer>();
        randomPath  = new ArrayList<Integer>();
        pathLongest  = new ArrayList<Integer>();
        path.add(startNode);
        floydWarshallResult.matrixWithListsOfPaths[startNode][finalNode].forEach((n) -> path.add(n.getW()));
        floydWarshallResult.matrixWithListsOfLongestPaths[startNode][finalNode].forEach((n) -> pathLongest.add(n.getW()));
        floydWarshallResult.matrixWithListsOfPaths[startNode][randomNumber].forEach((n) -> randomPath.add(n.getW()));
        floydWarshallResult.matrixWithListsOfPaths[randomNumber][finalNode].forEach((n) -> randomPath.add(n.getW()));
        if (kind ==1)
            this.route = path;
        else if (kind ==2)
            this.route = pathLongest;
        else if (kind ==3)
            this.route = randomPath;

    }

    void send(){
        status = PacketStatus.SENT;
    }
    void traverse(){
        //System.out.println(timestamp + "   " + status + "    " + route.get(timestamp) + "   " + finalNode);
        if (timestamp != 0 && status == PacketStatus.SENT){
            if(timestamp!=1)
                buffers.buffers.set(route.get(timestamp), buffers.buffers.get(route.get(timestamp))-1);
            if (route.get(timestamp)== finalNode) {
                status = PacketStatus.RECEIVED;
            }
            if(timestamp >= timeToLeave)
                status = PacketStatus.EXPIRED;
            if(buffers.buffers.get(route.get(timestamp)) >=  Buffer.bufferSize   )
                status = PacketStatus.LOST;
            if(timestamp!=0)
                timestamp++;
            if(status == PacketStatus.SENT) {
                delay = delay + buffers.buffers.get(route.get(timestamp));
                buffers.buffers.set(route.get(timestamp), buffers.buffers.get(route.get(timestamp)) + 1);
            }
        }
    }
    public int getHops(){
        return route.size();
    }

    public int getStartNode() {
        return startNode;
    }

    public void setStartNode(int startNode) {
        this.startNode = startNode;
    }

    public int getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(int finalNode) {
        this.finalNode = finalNode;
    }

    public int getTimeToLeave() {
        return timeToLeave;
    }

    public void setTimeToLeave(int timeToLeave) {
        this.timeToLeave = timeToLeave;
    }

    public PacketStatus getStatus() {
        return status;
    }

    public void setStatus(PacketStatus status) {
        this.status = status;
    }



}
