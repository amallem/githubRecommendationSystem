package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by anirudh on 11/28/15.
 */
@Entity
@Table(name = "location")
public class Location extends Model {

    public String repository_owner;

    public String repository_language;

    public String repository_owner_location;

    public static Finder<String, Location> find = new Finder<String, Location>(String.class, Location.class);
}
