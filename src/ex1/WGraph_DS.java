package ex1;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//






import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class WGraph_DS implements weighted_graph {
    private HashMap<Integer, node_info> nodes;
    public static double connectCount = 0.0D;
    public static int ModeCount = 0;

    public WGraph_DS() {
        connectCount = 0.0D;
        ModeCount = 0;
        this.nodes = new HashMap();
    }
    @Override
    public node_info getNode(int key) {
        return (node_info)this.nodes.get(key);
    }
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(this.nodes.containsKey(node1)&&this.nodes.containsKey(node2))
            return ((NodeInfo)this.getNode(node1)).getNi().contains(this.getNode(node2));
        return false;
    }

    @Override
    public double getEdge(int node1, int node2) {
        if(this.nodes.containsKey(node1)&&this.nodes.containsKey(node2)) {
            if(((NodeInfo)getNode(node1)).getWeightMap().containsKey(node2)&&((NodeInfo)getNode(node2)).getWeightMap().containsKey(node1))
                return ((NodeInfo) this.getNode(node1)).getWeight(node2);
        }
        return -1;
    }

    @Override
    public void addNode(int key) {
        NodeInfo n1= new NodeInfo();
        if(this.nodes.get(key)==null) {
            n1.setKey(key);
            this.nodes.put(key,n1);
            ++ModeCount;
        }
    }
    @Override
    public void connect(int node1, int node2,double w) {
        if (node1 != node2 && this.nodes.containsKey(node1) && this.nodes.containsKey(node2)) {
            if (!((NodeInfo)this.getNode(node1)).getNi().contains(this.getNode(node2))) {
                ((NodeInfo)this.getNode(node1)).addNi(this.getNode(node2));
                connectCount += 0.5D;
                ((NodeInfo)this.getNode(node1)).addToWeightMap(node2,w);
            }

            if (!((NodeInfo)this.getNode(node2)).getNi().contains(this.getNode(node1))) {
                ((NodeInfo)this.getNode(node2)).addNi(this.getNode(node1));
                connectCount += 0.5D;
                ((NodeInfo)this.getNode(node2)).addToWeightMap(node1,w);
            }

            ++ModeCount;
        }

    }

    public Collection<node_info> getV() {
        new HashMap();
        HashMap n = this.nodes;
        return n.values();
    }

    public Collection<node_info> getV(int node_id) {
        return ((NodeInfo)this.getNode(node_id)).getNi();
    }

    public node_info removeNode(int key) {
        NodeInfo temp = new NodeInfo();
        temp=((NodeInfo) this.getNode(key)).copy();
        if (this.getNode(key) != null && this.nodes.containsKey(key)) {
            Iterator var3 = ((NodeInfo)this.getNode(key)).getNi().iterator();

            while(var3.hasNext()) {
                node_info n = (node_info)var3.next();
                if (((NodeInfo)n).getNi().remove(this.getNode(key))) {
                    ((NodeInfo)n).removeWeight(key);
                    --connectCount;
                    ++ModeCount;
                }
            }

            this.nodes.remove(key);
            ++ModeCount;
        }
        return temp;
    }

    public void removeEdge(int node1, int node2) {
        if(this.nodes.containsKey(node1)&&this.nodes.containsKey(node2)){
            if (((NodeInfo)this.getNode(node1)).getNi().contains(this.getNode(node2))) {
                ((NodeInfo)this.getNode(node1)).getNi().remove(this.getNode(node2));
                connectCount -= 0.5D;
                ++ModeCount;
            }

            if (((NodeInfo)this.getNode(node2)).getNi().contains(this.getNode(node1))) {
                ((NodeInfo)this.getNode(node2)).getNi().remove(this.getNode(node1));
                connectCount -= 0.5D;
                ++ModeCount;
            }}
        if(((NodeInfo)this.getNode(node2)).getWeightMap().containsKey(node1))
            ((NodeInfo)this.getNode(node2)).getWeightMap().remove(node1);
        if(((NodeInfo)this.getNode(node1)).getWeightMap().containsKey(node2))
            ((NodeInfo)this.getNode(node1)).getWeightMap().remove(node2);

    }

    public int edgeSize() {
        return (int) connectCount;
    }

    public int nodeSize() {
        return this.nodes.isEmpty() ? 0 : this.nodes.size();
    }

    @Override
    public String toString() {
        String nodesString="";
        for(node_info n1: nodes.values())
            nodesString+=n1+",";
        return "WGraph_DS{" +
                "nodes=" + nodesString +
                '}'+"\n";
    }
    public WGraph_DS copy()
    {
        WGraph_DS g1=new WGraph_DS();
        Iterator var3 = this.getNods().values().iterator();
        while(var3.hasNext()) {
            NodeInfo m = (NodeInfo)var3.next();
            g1.getNods().put(m.getKey(),m.copy());
        }
        return g1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_DS wGraph_ds = (WGraph_DS) o;
        for(node_info n1: wGraph_ds.nodes.values())
            if(!nodes.containsKey(n1.getKey()))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }

    public int getMC() {
        return ModeCount;
    }

    public HashMap getNods() {
        return this.nodes;
    }
}

