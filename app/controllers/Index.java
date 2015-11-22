package controllers;

import java.util.*;

import com.avaje.ebean.PagedList;
import models.Language;
import models.Recommendation;
import play.mvc.*;
import views.html.index;

import static play.libs.Json.toJson;

public class Index extends Controller {

     int rowCount = Language.find.findRowCount();
     PagedList<Recommendation> recoList = Recommendation.query.findPagedList(0,1000);



    public Result index() {
        GenerateGraph.generate(recoList.getList());
        GenerateGraph.PrintUG(recoList.getList());
        Result res = ok(index.render("Hi "+ recoList.getTotalPageCount()));
        return res;
    }
}