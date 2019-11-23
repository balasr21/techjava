package com.techjava.lambda;

import com.techjava.lambda.com.techjava.lambda.dto.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CollectionsCompare {

    public static void main(String[] args){

        // Create list of People

        List<Person> people=Arrays.asList(new Person("Bala","Murugan",32),
                                          new Person("Ashok","Kumar",43),
                                          new Person("Anand","Krishnan",53)
                );


        // Sort by Age

        /////////////////////////////////////////////////////////////////////////////////
        // Before Java 8
        /*        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.valueOf(o1.getAge()).compareTo(Integer.valueOf(o2.getAge()));
            }
        });*/
        /////////////////////////////////////////////////////////////////////////////////
        //    Sort using Java 8

        Collections.sort(people,(o1,o2)->Integer.valueOf(o1.getAge()).compareTo(Integer.valueOf(o2.getAge())));

        // Print all Values in list
        System.out.println("People List post sort");
        people.stream().forEach(o-> System.out.println(o));

        // Print all last name begining with K

        // Before Java 8
        /////////////////////////////////////////////////////////////////////////////////
        /*
        printBasedOnCondition(people, new FilteringCondition() {
            @Override
            public boolean check(Person p) {
                return p.getLastName().startsWith("K");
            }
        });
        */
        /////////////////////////////////////////////////////////////////////////////////
        // Print all last name begining with K using lambda expressions - Java 8

        System.out.println("List of people starting with K");
        printBasedOnCondition(people, (p)->p.getLastName().startsWith("K"),(p)-> System.out.println(p));



        // Print all values
        System.out.println("Printing all values");
        printBasedOnCondition(people,(p)->true,(p)-> System.out.println(p));

        // Print all values using Method reference
        System.out.println("Printing all values using Method Reference ");
        printBasedOnCondition(people,(p)->true,System.out::println);

    }

    // Before Java 8
    /////////////////////////////////////////////////////////////////////////////////
    /*
    private static void printBasedOnCondition(List<Person> people, FilteringCondition condition) {
        for(Person p:people){
            if(condition.check(p)){
                System.out.println("Last names starting with expected condition " + p);
            }
        }
    }

    */
    /////////////////////////////////////////////////////////////////////////////////

    // Post Java 8

    private static void printBasedOnCondition(List<Person> people, Predicate<Person> condition, Consumer<Person> consumer) {
        for(Person p:people){
            if(condition.test(p)){
                consumer.accept(p);
            }
        }
    }

    interface FilteringCondition{
        boolean check(Person p);
    }

}
