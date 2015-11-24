package controllers;

import com.avaje.ebean.PagedList;
import models.Language;
import models.Recommendation;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Index extends Controller {

     int rowCount = Language.find.findRowCount();
    PagedList<Recommendation> recoList;



    public Result index() {
        for (int i = 0; i <= 3; i++) {
            recoList = Recommendation.query.findPagedList(i, 100000);
            GenerateGraph.generate(recoList.getList());
        }
        GenerateGraph.PrintGS(recoList.getList());
        RecomSystem.Initial();
        RecomSystem.BuildTree(GenerateGraph.userGraph, GenerateGraph.repoGraph, "TwistedUmbrella");
        RecomSystem.Print();
//        GenerateGraph.Print("jsDAV_ajaxorg");
        Result res = ok(Json.toJson(RecomSystem.Print()));
        return res;
    }
}