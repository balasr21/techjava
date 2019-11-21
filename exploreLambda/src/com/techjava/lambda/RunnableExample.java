package com.techjava.lambda;

public class RunnableExample {
    public static void main(String[] args){

        Thread myThread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello!!");
            }
        });

        myThread.run();

        myThread=new Thread(()-> System.out.println("Helloo!"));
        myThread.run();

    }



}
