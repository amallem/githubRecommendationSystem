package controllers;

import models.GraphNode;
import models.Recommendation;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

/**
 * Created by anirudh on 11/13/15.
 */
public class GenerateGraph {

    public static HashMap<String, List<GraphNode>> userGraph = new HashMap<>();
    public static HashMap<String, List<GraphNode>> repoGraph = new HashMap<>();

    public static final int PEW = 1;
    public static final int WEW = 2;

    public static void generate(List<Recommendation> data){
        int listsize = data.size();
        for(int i = 1; i <= listsize; i++){
            Recommendation R = data.get(i-1);
            String rn = R.repository_name;
            String ro = R.repository_owner;
            String user = R.actor;
            String typ = R.type;
            String rid  = rn + " " + ro;
            double weight;
            switch (typ){
                case "PushEvent":
                    weight = PEW;
                    break;
                case "WatchEvent":
                    weight = WEW;
                    break;
                default:
                    weight = 99999;
                    break;
            }

            GraphNode repo = new GraphNode(rid, weight);
            GraphNode u = new GraphNode(user, weight);

            if(!userGraph.containsKey(user)){
                List<GraphNode> listrepo = new LinkedList();
                listrepo.add(repo);
                userGraph.put(user, listrepo);
            }
            else{
                List<GraphNode> listrepo = userGraph.get(user);
                int f = listrepo.indexOf(repo);
                if(f == -1){
                    listrepo.add(repo);
                }
                else if(f >= 0){
                    GraphNode s = listrepo.get(f);
                    double oldweight = s.getEW();
                    double newweight = repo.getEW();
                    double finalweight = oldweight*newweight/(oldweight + newweight);
                    s.setEW(finalweight);
                }

            }

            if(!repoGraph.containsKey(rid)){
                List<GraphNode> listuser = new LinkedList();
                listuser.add(u);
                repoGraph.put(rid, listuser);
            }
            else{
                List<GraphNode> listuser = repoGraph.get(rid);
                int f = listuser.indexOf(u);
                if(f == -1){
                    listuser.add(u);
                }
                else if(f >= 0){
                    GraphNode s = listuser.get(f);
                    double oldweight = s.getEW();
                    double newweight = u.getEW();
                    double finalweight = oldweight*newweight/(oldweight + newweight);
                    s.setEW(finalweight);
                }
            }
        }

    }

    public static void PrintUG(List<Recommendation> data){
        for(int i=1; i <= data.size(); i++){
            Recommendation R = data.get(i-1);
            System.out.print(R.actor + "\t");
            System.out.println(userGraph.get(R.actor));
        }
    }

    public static void main(String[] args){
        List<Recommendation> r1 = Recommendation.query.findPagedList(0,10).getList();
        generate(r1);
        PrintUG(r1);
        System.out.println("czy");
    }
}
