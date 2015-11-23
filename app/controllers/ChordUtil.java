package controllers;

import com.avaje.ebean.Ebean;
import models.Lang;
import models.Language;
import play.libs.*;
import play.mvc.*;
import play.libs.Json;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anirudh on 11/22/15.
 */
public class ChordUtil extends Controller {
    int[][] langMatrix;
    private static final int size = 6;
    List<String> java = new ArrayList<>();
    List<String> python = new ArrayList<>();
    List<String> c = new ArrayList<>();
    List<String> ruby = new ArrayList<>();
    List<String> cplusplus = new ArrayList<>();
    List<String> javascript = new ArrayList<>();

    public Result index(){
        if(langMatrix == null){
            initialize();
            populateMatrix();
        }
        return ok(visual.render(langMatrix));
    }

    private void populateMatrix() {
        List<String> out;
        List<String> in;
        for(int i=0;i<size;i++){
            out = getLangList(i);
            for(int j = 0; j<size; j++){
                in = getLangList(j);
                if (i == j) {
                    langMatrix[i][i] = out.size();
                } else if (langMatrix[j][i] != 0) {
                    langMatrix[i][j] = langMatrix[j][i];
                }
                else {
                    langMatrix[i][j] = getMergedCount(in, out);
                }
            }
            System.out.println("Populating....");
        }
    }

    private int getMergedCount(List<String> in, List<String> out) {
        List<String> result = new ArrayList<>();

        for(String s: in){
            if(out.contains(s)){
                result.add(s);
            }
        }
        return result.size();
    }

    private List<String> getLangList(int val) {
        Lang lang = Lang.getLang(val);
        switch (lang){
            case java:
                return java;
            case python:
                return python;
            case ruby:
                return ruby;
            case c:
                return c;
            case javascript:
                return javascript;
            case cplusplus:
                return cplusplus;
            default:
                break;
        }
        return null;
    }

    private void initialize(){
        langMatrix = new int[size][size];
        for(Lang outerlang: Lang.values()){
            switch(outerlang){
                case java:
                    for(Language lang : Ebean.find(Language.class).where().ieq("repo_lang","java").findList()){
                        java.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case python:
                    for(Language lang : Ebean.find(Language.class).where().ieq("repo_lang","python").findList()){
                        python.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case javascript:
                    for(Language lang : Ebean.find(Language.class).where().ieq("repo_lang","javascript").findList()){
                        javascript.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case c:
                    for(Language lang : Ebean.find(Language.class).where().ieq("repo_lang","c").findList()){
                        c.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case cplusplus:
                    for(Language lang : Ebean.find(Language.class).where().ieq("repo_lang","cplusplus").findList()){
                        cplusplus.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case ruby:
                    for(Language lang : Ebean.find(Language.class).where().ieq("repo_lang","ruby").findList()){
                        ruby.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                default:

                    break;
            }
        }
    }
}
