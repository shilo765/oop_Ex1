package ex1;

import java.util.List;
import java.util.PriorityQueue;

public class WGraph_Algo  implements weighted_graph_algorithms{
    private  WGraph_DS g1=new WGraph_DS();
    private PriorityQueue<NodeInfo> pq=new PriorityQueue();
            public static int nodeCount=0;
    @Override
    public void init(weighted_graph g) {

    }

    @Override
    public weighted_graph getGraph() {
        return this.g1;
    }

    @Override
    public weighted_graph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        nodeCount=0;
        NodeInfo temp=new NodeInfo();
        if(!g1.getNods().contains(g1.getNode(src))||!g1.getNods().contains(g1.getNode(dest)))
            return -1;
        nodeCount=1;
        if(src==dest)
          return 0;
        g1.getNode(src).setTag(0);
        pq.add((NodeInfo)g1.getNode(src));
        while (!pq.isEmpty())
        {
            temp=pq.poll();
            for (node_info n1: temp.getNi()) {
                if(n1.getTag()==-1||n1.getTag()>temp.getTag()+g1.getEdge(n1.getKey(),temp.getKey()))
                {
                    if(n1.getTag()==-1)
                        nodeCount++;
                    else
                        pq.remove(n1);
                    n1.setTag(temp.getTag()+g1.getEdge(n1.getKey(),temp.getKey()));
                    pq.add((NodeInfo)n1);

                }
            }
        }
        return g1.getNode(dest).getTag();
    }

    @Override
    public List<node_info> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
