package controllers;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import models.GraphNode;

/**
 * Created by cheng on 11/22/15.
 */
public class RecomSystem {
    public static HashMap<String, DisPair> actor = new HashMap<>();
    public static HashMap<String, DisPair> rid = new HashMap<>();
    public static List<Double> Disc = new LinkedList<>();
    public static Stack<String> Path = new Stack<>();
    public static List<String> OnPath = new LinkedList<>();
    public static List<String> S = new LinkedList<>();
    public static List<String> Q = new LinkedList<>();
    public static final int MAXD = 30;
    public static final int MAXS = 100;
    public static List<String> repolist = new LinkedList<>();
    public static List<String> userlist = new LinkedList<>();


    public static void Initial(){
        Set<String> actorname = GenerateGraph.userGraph.keySet();
        Set<String> reponame = GenerateGraph.repoGraph.keySet();
        for(String e : actorname){
            actor.put(e, new DisPair());
        }
        for(String e : reponame){
            rid.put(e, new DisPair());
        }
    }

    public static void AllPaths(HashMap<String, List<GraphNode>> userGraph, HashMap<String, List<GraphNode>> repoGraph, String u, String t){
        if(OnPath.size() < MAXD){
            Path.push(u);
            OnPath.add(u);
            boolean IsActor = actor.containsKey(u);
            if(u.equals(t)){
                if(IsActor){
                    Disc.add(actor.get(u).getD());
                }
                else{
                    Disc.add(rid.get(u).getD());
                }
            }
            else{
                List<GraphNode> AdjList = userGraph.containsKey(u) ? userGraph.get(u) : repoGraph.get(u);
                for(GraphNode e : AdjList){
                    if(!OnPath.contains(e.getName())){
                        double vd;
                        double ud;
                        if(IsActor) ud = actor.get(u).getD();
                        else ud = rid.get(u).getD();
                        vd = ud + e.getEW();
                        if(actor.containsKey(e.getName())){
                            actor.get(e.getName()).setD(vd);
                        }
                        else{
                            rid.get(e.getName()).setD(vd);
                        }
                        AllPaths(userGraph, repoGraph, e.getName(), t);
                    }
                }

            }
            Path.pop();
            OnPath.remove(u);
        }
    }

    public static void BuildTree(HashMap<String, List<GraphNode>> userGraph, HashMap<String, List<GraphNode>> repoGraph, String s){
        Q.add(s);
        while(Q.size() > 0 && S.size() <= MAXS ){
            String u = ExtractMin(Q);
            S.add(u);
            List<GraphNode> AdjList = userGraph.containsKey(u) ? userGraph.get(u) : repoGraph.get(u);
            for(GraphNode e : AdjList){
                String ename = e.getName();
                if(!(S.contains(ename) || Q.contains(ename))){
                    OnPath.clear();
                    AllPaths(userGraph, repoGraph, s, ename);
                    if(actor.containsKey(ename)){
                        actor.get(ename).setDis(ComputeDis());
                        Q.add(ename);
                    }
                    else{
                        rid.get(ename).setDis(ComputeDis());
                        Q.add(ename);
                    }
                }
            }
        }
    }


    public static String ExtractMin(List<String> q){
        String min = q.get(0);
        double mindis = actor.containsKey(min) ? actor.get(min).getDis() : rid.get(min).getDis();
        double edis = 999;
        for(String e : q){
            edis = actor.containsKey(e) ? actor.get(e).getDis() : rid.get(e).getDis();
            if(edis < mindis){
                mindis = edis;
                min = e;
            }
        }
        q.remove(min);
        return min;
    }

    public static double ComputeDis(){
        double sum = 0;
        for(double e : Disc){
            e = 1/e;
            sum = sum + e;
        }
        Disc.clear();
        return 1/sum;
    }

    public static void getRepo(){
        for(String e : S){
            if(rid.containsKey(e)){
                repolist.add(e);
            }
        }
        System.out.println(repolist);
    }

    public static void getUser(){
        for(String e : S){
            if(actor.containsKey(e)){
                userlist.add(e);
            }
        }
        userlist.remove(0);
        System.out.println(userlist);
    }

    public static void getUserForRepo(){
        for(String e : S){
            if(actor.containsKey(e)){
                userlist.add(e);
            }
        }
//        userlist.remove(0);
        System.out.println(userlist);
    }

    public static List<String> Print() {
        System.out.println(S);
        return S;
    }


}






class DisPair{
    private double d;
    private double dis;

    public double getD(){
        return d;
    }

    public void setD(double d){
        this.d = d;
    }

    public double getDis(){
        return dis;
    }

    public void setDis(double dis){
        this.dis = dis;
    }
}
