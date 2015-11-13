package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by anirudh on 11/13/15.
 */
@Entity
@Table(name="recommendation")
public class Recommendation extends Model {

    public String repo_name;

    public String repo_owner;

    public String actor;

    public String type;
}
