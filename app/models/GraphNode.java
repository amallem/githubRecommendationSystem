package models;

/**
 * Created by anirudh on 11/13/15.
 */
public class GraphNode {

    private boolean isRepo;

    private String name;

    private double edgeWeight;

    public GraphNode(String name, double edgeWeight){
        this.name = name;
        this.edgeWeight = edgeWeight;
    }

    public double getEW(){
        return edgeWeight;
    }

    public void setEW(double w){
        edgeWeight = w;
    }

    public String getName() { return name; }

    public boolean equals(Object otherObject){
        GraphNode other = (GraphNode) otherObject;
        return name.equals(other.name);
    }

    public String toString(){
        return name + "\t" + edgeWeight + "\t";
    }
}
