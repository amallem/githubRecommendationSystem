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
    private static final int size = 15;
    List<String> java = new ArrayList<>();
    List<String> python = new ArrayList<>();
    List<String> c = new ArrayList<>();
    List<String> ruby = new ArrayList<>();
    List<String> cplusplus = new ArrayList<>();
    List<String> javascript = new ArrayList<>();
    List<String> scala = new ArrayList<>();
    List<String> php = new ArrayList<>();
    List<String> csharp = new ArrayList<>();
    List<String> haskell = new ArrayList<>();
    List<String> perl = new ArrayList<>();
    List<String> coffeescript = new ArrayList<>();
    List<String> groovy = new ArrayList<>();
    List<String> actionscript = new ArrayList<>();
    List<String> clojure = new ArrayList<>();

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
                System.out.println("Populating....." + i + "," + j);
                if (i == j) {
                    langMatrix[i][i] = out.size();
                } else if (langMatrix[j][i] != 0) {
                    langMatrix[i][j] = langMatrix[j][i];
                }
                else {
                    langMatrix[i][j] = getMergedCount(in, out);
                }
            }
            System.out.println("Populating Done...." + i);
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
            case actionscript:
                return actionscript;
            case scala:
                return scala;
            case perl:
                return perl;
            case php:
                return php;
            case clojure:
                return clojure;
            case csharp:
                return csharp;
            case groovy:
                return groovy;
            case haskell:
                return haskell;
            case coffeescript:
                return coffeescript;
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
                    for (Language lang : Ebean.find(Language.class).where().ieq("repo_lang", "c++").findList()) {
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
                case scala:
                    for (Language lang : Ebean.find(Language.class).where().ieq("repo_lang", "scala").findList()) {
                        scala.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case clojure:
                    for (Language lang : Ebean.find(Language.class).where().ieq("repo_lang", "clojure").findList()) {
                        clojure.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case csharp:
                    for (Language lang : Ebean.find(Language.class).where().ieq("repo_lang", "csharp").findList()) {
                        csharp.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case groovy:
                    for (Language lang : Ebean.find(Language.class).where().ieq("repo_lang", "groovy").findList()) {
                        groovy.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case haskell:
                    for (Language lang : Ebean.find(Language.class).where().ieq("repo_lang", "haskell").findList()) {
                        haskell.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case perl:
                    for (Language lang : Ebean.find(Language.class).where().ieq("repo_lang", "perl").findList()) {
                        perl.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case actionscript:
                    for (Language lang : Ebean.find(Language.class).where().ieq("repo_lang", "actionscript").findList()) {
                        actionscript.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case coffeescript:
                    for (Language lang : Ebean.find(Language.class).where().ieq("repo_lang", "coffeescript").findList()) {
                        coffeescript.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                case php:
                    for (Language lang : Ebean.find(Language.class).where().ieq("repo_lang", "php").findList()) {
                        php.add(lang.repo_name);
                    }
                    System.out.println("Done " + outerlang.name());
                    break;
                default:

                    break;
            }
        }
    }
}
