package org.example;

import java.util.*;

public class RandomNumber {

    public static void main(String[] args) {

        //randomNo();


        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);


       int highestNo =  findHighestNo(numbers);
        System.out.println("Highest no is "+highestNo);



    }

    private static void randomNo() {
        Random random = new Random();
        random
        .ints(0,10)
        .limit(10)
        .forEach(System.out::println);
    }

    private static int findHighestNo(List<Integer> numbers) {

        //return numbers.stream().sorted(Comparator.reverseOrder()).limit(2).findFirst().get();

        //highest no using summary Statistics

        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt(x -> x).summaryStatistics();

        return intSummaryStatistics.getMax();
    }


}
