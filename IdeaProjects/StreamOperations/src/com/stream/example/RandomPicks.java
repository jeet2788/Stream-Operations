package com.stream.example;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RandomPicks {
    public static void main(String[] args) {

        String input = "swiss";
        Character result = getFirstNonRepeatingCharacter(input);

        Character lastCharacter = getLastNonRepeatingCharacter(input);

        if (result != null) {
            System.out.println("First non-repeating character in \"" + input + "\" is: " + result);
        } else {
            System.out.println("No non-repeating character found in \"" + input + "\"");
        }

        if (lastCharacter != null) {
            System.out.println("First non-repeating character in \"" + input + "\" is: " + lastCharacter);
        } else {
            System.out.println("No non-repeating character found in \"" + input + "\"");
        }
        List<String> cityList = List.of("Paris", "Bangalore", "SanFrancisco", "Rome");

        System.out.println(getLongestLength(cityList));

    }

    /**
     * Return the first nonrepeating character from a String
     * @param str
     * @return
     */
    private static Character getFirstNonRepeatingCharacter(String str) {
        return str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,
                        Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

    }

    /**
     * Return the Last nonrepeating character from a String
     * @param str
     * @return
     */
    private static Character getLastNonRepeatingCharacter(String str){
        return str.chars().mapToObj(c->(char)c).collect(
                Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet().stream()
                .filter(e->e.getValue()==1)
                .reduce((first,last)->last)
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    /**
     * Return the city which has the highest length
     */
    private static  String getLongestLength(List<String> cityList){
        return cityList.stream().max(Comparator.comparingInt(String::length)).orElse(null);
    }


}
