package com.techjava.lambda;

import com.techjava.lambda.com.techjava.lambda.dto.Person;
import com.techjava.lambda.com.techjava.lambda.dto.Score;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class StreamExamples {

    public static void main(String args[]){

        // Create list of People

        List<Person> people= Arrays.asList(new Person("Bala","Murugan",32),
                new Person("Ashok","Kumar",43),
                new Person("Anand","Krishnan",53),
                new Person("Suse","Sundar",27)
        );

        // Print First name all in for each
        System.out.println("Print First name all in for each");
        people.stream().forEach(p-> System.out.println(p.getFirstName()));

        // Sort list based on age
        System.out.println("Print Sorted list based on Age");
        people.sort(Comparator.comparingInt(Person::getAge));

        people.stream().forEach(p-> System.out.println(p.getFirstName()));

        // Count of people with Age > 30
        System.out.println("Count of people with Age>30");
        System.out.println(people.stream().filter(p->p.getAge()>30).count());


        List<Score> score= Arrays.asList(new Score(1l,"Bala", "S1" ,50),
                                        new Score(1l,"Bala", "S2" ,80),
                                        new Score(1l,"Bala", "S3" ,55),
                                        new Score(1l,"Bala", "S4" ,60),
                                        new Score(1l,"Bala", "S5" ,35),
                                        new Score(1l,"Suse", "S1" ,30),
                                        new Score(1l,"Suse", "S2" ,60),
                                        new Score(1l,"Suse", "S3" ,95),
                                        new Score(1l,"Suse", "S4" ,65),
                                        new Score(1l,"Suse", "S5" ,58),
                                        new Score(1l,"Ashok", "S1" ,67),
                                        new Score(1l,"Ashok", "S2" ,55),
                                        new Score(1l,"Ashok", "S3" ,99),
                                        new Score(1l,"Ashok", "S4" ,100),
                                        new Score(1l,"Ashok", "S5" ,98)
        );

        //Getting unique persons

        List<Person> persons=new ArrayList<>();

        score.stream().collect(Collectors.groupingBy(
                groupingCond -> persons.add(new Person(groupingCond.getName()))));

        persons.stream().distinct().collect(Collectors.toList());

        // Group list by name and print score

        Map<String, IntSummaryStatistics> scoreSummary = score.stream().collect(Collectors.groupingBy(Score::getName,
                Collectors.summarizingInt(Score::getScore)));

        for(Map.Entry<String,IntSummaryStatistics> sc:scoreSummary.entrySet()){
            System.out.println("Total score for " + sc.getKey()+ "::" +sc.getValue().getSum());
        }

        // Get all subjects lists by name

        Map<String, String> subjectLists = score.stream().collect(Collectors.groupingBy(groupingCond -> groupingCond.getName(),
                Collectors.mapping(Score::getSubject, Collectors.joining(",", "Subject lists:[", "]"))));

        for(Map.Entry<String,String> sc:subjectLists.entrySet()){
            System.out.println("User " + sc.getKey()+ " has " +sc.getValue());
        }


        // AnyMatch

        boolean personsWithStartingCharB = score.stream().anyMatch(p -> p.getName().startsWith("B"));

        System.out.println("Person with Starting Character B exists :"+ personsWithStartingCharB);


        //Print all total score
        int totalScore=score.stream().map(Score::getScore).reduce(0,(a,b)->a+b);

        System.out.println("Total score of all " + totalScore);


    }


}
