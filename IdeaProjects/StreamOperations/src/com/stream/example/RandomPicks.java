package com.stream.example;

import java.util.LinkedHashMap;
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
    private static Character getLastNonRepeatingCharacter(String str){
        return str.chars().mapToObj(c->(char)c).collect(
                Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet().stream()
                .filter(e->e.getValue()==1)
                .reduce((first,last)->last)
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
