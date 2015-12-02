package models;

/**
 * Created by anirudh on 11/29/15.
 */
public class Geo {

    public String city;

    public String lang;

    public int frequency;

    public Geo(String city, String lang, int freq) {
        this.city = city;
        this.lang = lang;
        this.frequency = freq;
    }
}
