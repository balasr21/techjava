package com.techjava.lambda.com.techjava.lambda.dto;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {

    public static void main(String args[]){

        CompletedFuture<?> receiveTask;

        receiveTask=testCompletable();

        while(!receiveTask.isDone()){
            System.out.println("Waiting for task to complete"+ Thread.currentThread().getName());
        }

    }

    public static CompletedFuture<?> testCompletable(){
        CompletableFuture<?> currentTask = new CompletableFuture<>();


        CompletableFuture.runAsync(()->{
           if(!currentTask.isCancelled()) {
               for (long i = 1; i < 5; i++) {
                   System.out.println("Inside CompletetableFutureMethod {}"+Thread.currentThread().getName() + " " + i);
                   waitTime();
               }
           }
        });
        return testCompletable();
    }

    public static void waitTime(){
        try {
             Thread.sleep(1000);
            System.out.println("Wait time in Thread " + Thread.currentThread().getName());
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }

}
