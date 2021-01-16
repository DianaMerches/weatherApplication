package ro.mta.se.lab.model;

import java.util.ArrayList;

public class Country {

    ArrayList<String>Name;
    ArrayList<String>City;
    ArrayList<String>Lat;
    ArrayList<String>Lon;

    public Country() {
        Name = new ArrayList<String>();
        City = new ArrayList<String>();
        Lat = new ArrayList<String>();
        Lon = new ArrayList<String>();
    }

    public void setName(String name) {
        Name.add(name);
    }

    public void setCity(String city) {
        City.add(city);
    }

    public void setLat(String lat) {
        Lat.add(lat);
    }

    public void setLon(String lon) {
        Lon.add(lon);
    }

    public ArrayList<String> getName() {
        return Name;
    }

    public ArrayList<String> getCity() {
        return City;
    }

    public ArrayList<String> getLat() {
        return Lat;
    }

    public ArrayList<String> getLon() {
        return Lon;
    }
}
