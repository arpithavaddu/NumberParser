package com.number.parser.numberparser;

import java.util.Map;
import java.util.Optional;

public class NumberParser {

    public static final String PREFIX = "+";
    private final Map<String, Integer> countryCodes;
    private final Map<String, String> nationalTrunkPrefixes;

    public NumberParser(Map<String, Integer> countryCodes, Map<String, String> nationalTrunkPrefixes) {
        this.countryCodes = countryCodes;
        this.nationalTrunkPrefixes = nationalTrunkPrefixes;
    }

    public String parse(String dialledNumber, String userNumber) {

        Optional<Map.Entry<String, Integer>> code = countryCodes.entrySet().stream()
                .filter(e -> userNumber.startsWith(PREFIX + e.getValue())).findAny();
        if (code.isPresent()) {
            if (dialledNumber.startsWith(PREFIX)) {
                return dialledNumber;
            }
            Map.Entry<String, Integer> countryCode = code.get();
            String countryPrefix = nationalTrunkPrefixes.get(countryCode.getKey());
            if (dialledNumber.startsWith(countryPrefix)) {
                return new StringBuilder().append(PREFIX).append(countryCode.getValue())
                        .append(dialledNumber.substring(countryPrefix.length())).toString();
            } else {
                throw new InvalidNumberException("Invalid number dialled");
            }
        } else {
            throw new InvalidNumberException("Invalid user number");
        }
    }
}
