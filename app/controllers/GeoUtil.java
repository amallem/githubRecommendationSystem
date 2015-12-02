package controllers;

import com.avaje.ebean.PagedList;
import models.Geo;
import models.Location;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GeoUtil extends Controller {


    List<Geo> locationMatrix = new ArrayList<Geo>();

    public Result index() {
        populateMatrix("java");
        return ok(location.render(locationMatrix));
    }

    private void populateMatrix(String lang) {
        List<Location> locationPagedList = Location.find.where().ieq("repository_language", lang).findList();
        HashMap<String, Integer> frequency = new HashMap<>();
        int count;
        for (Location location : locationPagedList) {
            String[] loc = location.repository_owner_location.split(",");
            if (frequency.get(loc[0]) == null) {
                frequency.put(loc[0], 1);
            } else {
                count = frequency.get(loc[0]);
                frequency.replace(loc[0], ++count);
            }
        }

        Iterator<String> set = frequency.keySet().iterator();
        String place;
        while (set.hasNext()) {
            place = set.next();
            count = frequency.get(place);

            Geo res = new Geo(place, lang, count);
            locationMatrix.add(res);
        }
    }

}
