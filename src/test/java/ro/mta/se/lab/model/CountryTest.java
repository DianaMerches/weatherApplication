package ro.mta.se.lab.model;

import java.util.Iterator;

import static org.junit.Assert.*;

public class CountryTest {
    Country countryTest;

    @org.junit.Before
    public void setUp() throws Exception {
        countryTest=new Country();
        countryTest.setName("FR");
        countryTest.setCity("Paris");
        countryTest.setLat("48.8534");
        countryTest.setLon("2.3488");
    }

    @org.junit.Test
    public void getCountry() {
        Iterator iter = countryTest.getName().iterator();
        assertEquals(iter.next(),"FR");
    }

    @org.junit.Test
    public void getCity() {
        Iterator iter = countryTest.getCity().iterator();
        assertEquals(iter.next(),"Paris");
    }

    @org.junit.Test
    public void getLat() {
        Iterator iter = countryTest.getLat().iterator();
        assertEquals(iter.next(),"48.8534");
    }

    @org.junit.Test
    public void getLon() {
        Iterator iter = countryTest.getLon().iterator();
        assertEquals(iter.next(),"2.3488");
    }
}