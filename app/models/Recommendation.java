package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by anirudh on 11/13/15.
 */
@Entity
@Table(name="recommendation")
public class Recommendation extends Model {

    @Id
    public String repository_name;

    public String repository_owner;

    public String actor;

    public String type;

    public static Finder<String,Recommendation> query = new Finder<String, Recommendation>(String.class,Recommendation.class);
}
