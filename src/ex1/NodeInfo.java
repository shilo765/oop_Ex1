package ex1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
public class NodeInfo implements node_info,Comparable<NodeInfo> {
    private static int keyCount = 0;
    private int key = -1;
    private double tag = -1;
    private String info = "";
    private static double NodeEdges = 0.0D;
    private int lastNei = -1;
    private HashMap<Integer, node_info> nei;
    public static int neiCount;
    private HashMap<Integer,Double> weightMap;

    public NodeInfo() {
        this.key = ++keyCount;
        this.nei = new HashMap();
        this.weightMap=new HashMap<>();
        neiCount = 0;
    }
    public void setKey(int key)
    {
        this.key=key;
    }
    public void addToWeightMap(int node1,double w){this.weightMap.put(node1,w);}

    public double getWeight(int node1)
    {
        return this.weightMap.get(node1);
    }
    public HashMap getWeightMap(){ return this.weightMap;}

    public void removeWeight(int node1){this.weightMap.remove(node1);}

    public int getLastNei() {
        return this.lastNei;
    }

    public void setLastNei(int value) {
        this.lastNei = value;
    }
    @Override
    public int getKey() {
        return this.key == -1 ? 0 : this.key;
    }

    public Collection<node_info> getNi() {
        return this.nei.values();
    }

    public boolean hasNi(int key) {
        return this.nei.values().iterator().hasNext();
    }

    public void addNi(node_info t) {
        if (!this.equals(t)) {
            this.nei.put(t.getKey(), t);
            ++neiCount;
        }

    }

    public void removeNode(node_info node) {
        if (this.nei.containsKey(node.getKey())) {
            this.nei.remove(node.getKey());
            --neiCount;
        }

    }

    public int getNeiSize() {
        return neiCount;
    }
    @Override
    public double getTag() {
        return this.tag;
    }
    @Override
    public String getInfo() {
        return this.info;
    }

    public NodeInfo copy(NodeInfo n) {
        NodeInfo n2 = new NodeInfo();
        if (n == null) {
            return n2;
        } else {
            this.key = n.getKey();
            this.info = n.getInfo();
            this.tag = n.getTag();
            Iterator var3 = n.getNi().iterator();

            while(var3.hasNext()) {
                node_info m = (node_info)var3.next();
                this.nei.put(m.getKey(), m);
            }

            return this;
        }
    }
    @Override
    public void setInfo(String s) {
        this.info = s;
    }
    @Override
    public void setTag(double t) {
        this.tag = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeInfo nodeInfo = (NodeInfo) o;
        return key == nodeInfo.key &&
                Double.compare(nodeInfo.tag, tag) == 0 &&
                lastNei == nodeInfo.lastNei &&
                info.equals(nodeInfo.info) &&
                nei.equals(nodeInfo.nei);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, tag, info, lastNei, nei);
    }

    @Override
    public String toString() {
        return "ex1.NodeInfo{" +
                "key=" + key +
                ", tag=" + tag +
                ", info='" + info + '\'' +
                ", lastNei=" + lastNei +
                ", nei=" + nei +
                '}';
    }
    @Override
    public int compareTo(NodeInfo n1)
    {
        if(this.getTag()>n1.getTag())
            return 1;
        if (this.getTag()<n1.getTag())
            return -1;
        return 0;
    }
    public double getNodesEdges() {
        return NodeEdges;
    }
}

