package controllers;

import java.util.*;

import com.avaje.ebean.PagedList;
import models.Language;
import play.mvc.*;
import views.html.index;

import static play.libs.Json.toJson;

public class Index extends Controller {

     int rowCount = Language.find.findRowCount();

    public Result index() {
        Result res = ok(index.render("Hi "+ rowCount));
        return res;
    }
}