package models;

/**
 * Created by anirudh on 11/23/15.
 */
public enum Lang {
    java,
    python,
    javascript,
    ruby,
    c,
    cplusplus,
    scala,
    php,
    clojure,
    actionscript,
    haskell,
    csharp,
    groovy,
    coffeescript,
    perl;

    public static Lang getLang(int val){
        for(Lang lang: Lang.values()){
            if(lang.ordinal()==val){
                return lang;
            }
        }
        return null;
    }
}
