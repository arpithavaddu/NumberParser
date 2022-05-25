package com.number.parser.numberparser;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NumberParserTest {

    private NumberParser numberParser;

    @Before
    public void setup() {
        Map<String, Integer> countryCodes = new HashMap<>();
        countryCodes.put("UK", 44);
        countryCodes.put("FR", 33);
        countryCodes.put("US", 1);
        Map<String, String> nationalTrunkPrefixes = new HashMap<>();
        nationalTrunkPrefixes.put("UK", "0");
        nationalTrunkPrefixes.put("FR", "0");
        nationalTrunkPrefixes.put("US", "1");

        numberParser = new NumberParser(countryCodes, nationalTrunkPrefixes);
    }


    @Test
    public void shouldReturnUKIInternationaleNumberWhenDialledFromUKNumber() {
        String number = numberParser.parse("07277822334", "+447866866886");
        assertNotNull(number);
        assertEquals("+447277822334", number);
    }

    @Test
    public void shouldReturnFRInternationalNumberWhenDialledFromFRNumber() {
        String number = numberParser.parse("07277822334", "+337866866886");
        assertNotNull(number);
        assertEquals("+337277822334", number);
    }

    @Test
    public void shouldReturnUSInternationalNumberWhenDialledFromUSNumber() {
        String number = numberParser.parse("1312233244", "+1212233200");
        assertNotNull(number);
        assertEquals("+1312233244", number);
    }

    @Test
    public void shouldReturnUSInternationalNumberWhenDialledFromUKNumber() {
        String number = numberParser.parse("+1312233244", "+447866866886");
        assertNotNull(number);
        assertEquals("+1312233244", number);
    }

    @Test(expected = InvalidNumberException.class)
    public void shouldThrowInvalidNumberExceptionWhenDialledNumberDoesNotHaveExpectedNationalPrefix() {
        numberParser.parse("17277822334", "+447866866886");
    }

    @Test(expected = InvalidNumberException.class)
    public void shouldThrowInvalidNumberExceptionWhenUserNumberDoesNotHaveExpectedCountryCodePrefix() {
        numberParser.parse("07277822334", "+857866866886");
    }
}