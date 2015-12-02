package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.about;

/**
 * Created by cheng on 12/2/15.
 */
public class About extends Controller {
    public Result index() {
        return ok(about.render("about"));
    }
}
