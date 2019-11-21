package com.techjava.lambda;

import java.util.function.BiConsumer;

public class TypeInferenceLambdaExample {

    public static void main(String[] args){

        int[] a={1,2,3};
        int key=0;

        // Add and display elements
        performOperation(a,key,(v,k)-> System.out.println(v + k));

        // Multiply and display elements
        performOperation(a,key,(v,k)-> System.out.println(v * k));

        // Divide and display elements
        performOperation(a,key,(v,k)-> System.out.println(v / k));

    }

    private static void performOperation(int[] a, int key, BiConsumer<Integer,Integer> o) {
        for(int i=0;i<a.length;i++){
            o.accept(a[i],key);
        }

    }



}
