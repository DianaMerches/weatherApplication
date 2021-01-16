package ro.mta.se.lab.model;

import java.util.ArrayList;

public class WeatherModel {
    ArrayList<Country> country;

    public WeatherModel() {
        country=new ArrayList<Country>();
    }

    public ArrayList<Country> getCountry() {
        return country;
    }

    public void setCountry(ArrayList<Country> country) {
        this.country = country;
    }

    public void addCountry(Country countryToAdd){
        country.add(countryToAdd);
    }
}
