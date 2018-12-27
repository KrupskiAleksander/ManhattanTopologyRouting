package Elka.Soib.Network;

public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = E;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : adj(v))
                R.addEdge(w, v);
        return R;
    }

    public static Digraph generateManhattanDigraph(int size)
    {
        //Digraph R = new Digraph(size * size);
        Digraph R = new Digraph(4);
        R.addEdge(0,1);
        R.addEdge(1,2);
        R.addEdge(2,3);
        R.addEdge(3,0);
        return R;
    }

}
