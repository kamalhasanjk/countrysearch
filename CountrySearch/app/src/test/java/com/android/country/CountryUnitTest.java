package com.android.country;

import com.android.country.db.StringArrayConverter;

import org.junit.Test;

import static org.junit.Assert.*;


public class CountryUnitTest {



    @Test
    public void stringConversion() {
        String actual = new StringArrayConverter().toString(new String[]{"India","Europe"});
        // expected value is 212
        String expected = "[India, Europe]";
        // use this method because float is not precise
        assertEquals("Conversion from String[] to string", expected, actual);
    }



}