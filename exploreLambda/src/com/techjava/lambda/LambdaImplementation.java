package com.techjava.lambda;

/**
 * LambdaImplementation
 *
 *
 *
 */

public class LambdaImplementation {

    public static void main(String[] args){

        SumInterface lambdaAddData= (int a,int b) -> a+b;

        System.out.println(lambdaAddData.addInput(3,4));

    }

}
