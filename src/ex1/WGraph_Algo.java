package ex1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WGraph_Algo  implements weighted_graph_algorithms{
    private weighted_graph g1=new WGraph_DS();
    private PriorityQueue<NodeInfo> pq=new PriorityQueue();
    private  LinkedList<node_info> l1 =new LinkedList<>();
            public static int nodeCount=0;
    @Override
    public void init(weighted_graph g) {
        this.g1=g;
    }

    @Override
    public weighted_graph getGraph() {
        return this.g1;
    }
    public void setWGraph(WGraph_DS wg)
    {
        this.g1=wg.copy();
    }

    @Override
    public weighted_graph copy() {
        WGraph_DS wg=new WGraph_DS();
        wg=((WGraph_DS)this.g1).copy();
        return wg;
    }

    @Override
    public boolean isConnected() {
        if (this.g1.getV().size()==1||this.g1.getV().size()==0)
            return true;
        Iterator<node_info> iterator=this.g1.getV().iterator();
        node_info n1=new NodeInfo();
        n1=iterator.next();
        shortestPathDist(n1.getKey(),iterator.next().getKey());
        int temp=this.g1.getV().size();
        return (this.g1.getV().size()==nodeCount);
    }
    public void setTags()
    {
        for(node_info n1: g1.getV())
            n1.setTag(-1);
    }
    @Override
    public double shortestPathDist(int src, int dest) {
        this.pq.clear();
        setTags();
        nodeCount=0;
        NodeInfo temp=new NodeInfo();
        if(!g1.getV().contains(g1.getNode(src))||!g1.getV().contains(g1.getNode(dest)))
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
                    ((NodeInfo)n1).setLastNei(temp.getKey());
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
        this.l1.clear();
        double temp=shortestPathDist(src,dest);
        NodeInfo n1=new NodeInfo();
        if(src<1||dest<1)
            return null;
        if(g1.getV().size()==0)
            return null;
        if(!g1.getV().contains(g1.getNode(src))||!g1.getV().contains(g1.getNode(dest)))
            return null;
        if(src==dest){
          l1.addFirst(g1.getNode(src));
          return l1;}
        if(temp==-1)
            return null;
        n1=(NodeInfo)g1.getNode(dest);
        while(n1.getKey()!=src)
        {
            l1.addFirst(n1);
            n1=(NodeInfo)g1.getNode(n1.getLastNei());
        }
        l1.addFirst(n1);
        return l1;
    }

    @Override
    public boolean save(String file) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(".\\graph.txt"));
            bw.write(this.toString());
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_Algo that = (WGraph_Algo) o;
        return g1.equals(that.g1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(g1);
    }

    @Override
    public String toString() {
        return "WGraph_Algo{" +
                "g1=" + g1 +
                '}';
    }
}
