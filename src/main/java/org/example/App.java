package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        findTotalNumberOfElement(myList );

        findTheMaximumNumber(myList);

        String input = "Java articles are Awesome";
        findNonRepeatedCharacter(input);

        findFirstRepeatedCharacter(input);

        sortListOfInteger(myList);

        sortNumbersInDescendingOrder(myList);

        //check if array contains duplicate
        int[] num = {1,2,3,1};
        checkForDuplicate(num);



    }

    private static void checkForDuplicate(int[] num) {
        List<Integer> list = Arrays
                .stream(num)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(list);
    }

    private static void sortNumbersInDescendingOrder(List<Integer> myList) {

        System.out.println("Sorting in descending order");
        myList.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    private static void sortListOfInteger(List<Integer> myList) {
        myList.stream().sorted().forEach(System.out::println);

    }


    private static void findFirstRepeatedCharacter(String input) {
        input.chars()
                .mapToObj(s->Character.toLowerCase(Character.valueOf((char)s)))
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry->entry.getValue()>1)
                .map(entry->entry.getKey())
                .findFirst().ifPresent(System.out::println);
    }

    private static void findNonRepeatedCharacter(String input) {

        input.chars()
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .ifPresent(System.out::println);
    }

    private static void findTheMaximumNumber(List<Integer> myList) {
        myList.stream().max(Integer::compare).ifPresent(System.out::println);
    }



    private static void findTotalNumberOfElement(List<Integer> myList) {
        System.out.println(myList.stream().count());
    }
}
