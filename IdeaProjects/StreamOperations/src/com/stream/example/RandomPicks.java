package com.stream.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Root root = createSampleData();

        String resultOfHierarchy = getGrandChildName(root, 101, 201);
        System.out.println(resultOfHierarchy);
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

    /**
    *
     * Problem Statement
       Write a Java function using the Stream API that:
       Takes the Root object
       Finds the Child whose id = 101
       From that child, finds the GrandChild whose id = 201
       Returns the name of that grandchild
      (e.g., "Grand Child One")
      If no child/grandchild matches, return null.
     */
    /**
     * Expected Function Signature
     * public String getGrandChildName(Root root, int childId, int grandChildId)
     */
    public static String getGrandChildName(Root root, int childId, int grandChildId) {

        return Stream.of(root.getChild1(), root.getChild2(), root.getChild3())
                .filter(Objects::nonNull)
                .filter(child -> child.getId() == childId)        // find the correct child
                .flatMap(child -> child.getChildren().stream())   // flatten grandchildren
                .filter(gc -> gc.getId() == grandChildId)         // find correct grandchild
                .map(GrandChild::getName)
                .findFirst()
                .orElse(null);
    }





    private static Root createSampleData() {

        Root root = new Root();
        root.setId(1);
        root.setName("Parent Node");

        // ---------- Child 1 ----------
        Child child1 = new Child();
        child1.setId(101);
        child1.setName("Child One");

        GrandChild gc1 = new GrandChild();
        gc1.setId(201);
        gc1.setName("Grand Child One");

        GrandChild gc2 = new GrandChild();
        gc2.setId(202);
        gc2.setName("Grand Child Two");

        child1.setChildren(Arrays.asList(gc1, gc2));
        root.setChild1(child1);

        // ---------- Child 2 ----------
        Child child2 = new Child();
        child2.setId(102);
        child2.setName("Child Two");

        GrandChild gc3 = new GrandChild();
        gc3.setId(203);
        gc3.setName("Grand Child Three");

        child2.setChildren(Arrays.asList(gc3));
        root.setChild2(child2);

        // ---------- Child 3 ----------
        Child child3 = new Child();
        child3.setId(103);
        child3.setName("Child Three");
        child3.setChildren(Collections.emptyList());

        root.setChild3(child3);

        return root;
    }

}
