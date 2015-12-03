package controllers;

import models.Location;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GeoUtil extends Controller {


    List<List<String>> locationMatrix = new ArrayList<>();

    public Result index() {
        return ok(map.render("Hii"));
    }

    public Result getData() {
        return ok(Json.toJson(locationMatrix));
    }


    public Result populate() {
        String subject = Form.form().bindFromRequest().get("lang");
        locationMatrix.clear();
        populateMatrix(subject);
        return redirect(routes.GeoUtil.index());
    }

    private void populateMatrix(String lang) {
        List<Location> locationPagedList = Location.find.where().ieq("repository_language", lang).findList();
        HashMap<String, Integer> frequency = new HashMap<>();
        int count;
        for (Location location : locationPagedList) {
            String[] loc = location.repository_owner_location.split(",");
            int len = loc.length;
            if (frequency.get(loc[len - 1]) == null) {
                frequency.put(loc[len - 1], 1);
            } else {
                count = frequency.get(loc[len - 1]);
                frequency.replace(loc[len - 1], ++count);
            }
        }

        Iterator<String> set = frequency.keySet().iterator();
        String place;
        while (set.hasNext()) {
            place = set.next();
            count = frequency.get(place);
            if (StringUtils.isAlphanumeric(place)) {
                List<String> res = new ArrayList<>();
                res.add(place);
                res.add(lang);
                res.add(count + "");
                locationMatrix.add(res);
            }
        }
    }

}
