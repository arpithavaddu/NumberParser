package com.number.parser.numberparser;

import java.util.HashMap;
import java.util.Map;

public class NumberParserApplication {

    public void main(String[] args){
        Map<String, Integer> countryCodes = new HashMap<>();
        countryCodes.put("UK",44);
        countryCodes.put("FR",33);
        countryCodes.put("US",1);
        Map<String, String> nationalTrunkPrefixes = new HashMap<>();
        nationalTrunkPrefixes.put("UK", "0");
        nationalTrunkPrefixes.put("FR", "0");
        nationalTrunkPrefixes.put("US", "1");

        NumberParser numberParser = new NumberParser(countryCodes, nationalTrunkPrefixes);
        String number = numberParser.parse(args[0], args[1]);
        System.out.println("International number is "+number);
    }
}
