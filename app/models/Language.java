package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by anirudh on 11/13/15.
 */
@Entity
@Table(name = "language")
public class Language extends Model{

    public String repo_name;

    public String repo_lang;

    public static Finder<String,Language> find = new Finder<String,Language>(String.class, Language.class );

}
