package Elka.Soib.Network;

import java.util.ArrayList;
import java.util.List;

public class Packet {
    private int startNode;
    private int finalNode;
    private static int timeToLeave;
    private PacketStatus status;
    private int timestamp;
    private List<Integer> route;

    public Packet(int startNode, int finalNode)    {
        status = PacketStatus.NEW;
        timestamp = 1;
        this.startNode = startNode;
        this.finalNode = finalNode;
    }
    public void generateRoute(FloydWarshall floydWarshallResult){
        List<Integer> path = new ArrayList<Integer>();
        path.add(startNode);
        floydWarshallResult.matrixWithListsOfPaths[startNode][finalNode].forEach((n) -> path.add(n.getW()));
        this.route = path;
    }

    void send(){
        status = PacketStatus.SENT;
    }
    void traverse(){
        System.out.println(timestamp + "   " + status + "    " + route.get(timestamp) + "   " + finalNode);
        if (timestamp != 0 && status == PacketStatus.SENT){
            if (route.get(timestamp)== finalNode) {
                timestamp = 0;
                status = PacketStatus.RECEIVED;
            }
            if(timestamp >= timeToLeave)
                status = PacketStatus.EXPIRED;
            timestamp++;
        }
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
