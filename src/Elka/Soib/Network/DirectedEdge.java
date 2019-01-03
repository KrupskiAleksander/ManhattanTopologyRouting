package Elka.Soib.Network;

public class DirectedEdge {
    private int v;

    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;


    }

    public int from() {
        return v;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int to(){
        return w;
    }

    public double getWeight() {
        return weight;
    }

    public String toString()
    {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
