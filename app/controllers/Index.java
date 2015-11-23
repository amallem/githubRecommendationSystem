package controllers;

import com.avaje.ebean.PagedList;
import models.Language;
import models.Recommendation;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Index extends Controller {

     int rowCount = Language.find.findRowCount();
     PagedList<Recommendation> recoList = Recommendation.query.findPagedList(0,3000000);



    public Result index() {
        GenerateGraph.generate(recoList.getList());
        GenerateGraph.PrintGS(recoList.getList());
//        RecomSystem.Initial();
//        RecomSystem.BuildTree(GenerateGraph.userGraph, GenerateGraph.repoGraph, "TwistedUmbrella");
//        RecomSystem.Print();
//        GenerateGraph.Print("jsDAV_ajaxorg");
        Result res = ok(index.render("Hi "+ recoList.getTotalPageCount()));
        return res;
    }
}