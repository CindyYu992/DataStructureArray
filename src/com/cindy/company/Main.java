package com.cindy.company;

import java.util.Random;

public class Main {
    public static  int opCount = 100000;
    //测试使用q运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
    private static double testQueue(QueueInterface<Integer>q,int opcount){
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i=0;i<opcount;i++){
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i=0;i<opcount;i++){
            q.dequeue();
        }

        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double timeA = testQueue(arrayQueue,opCount);

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double timeB = testQueue(loopQueue,opCount);

        System.out.println("timeA="+timeA);
        System.out.println("timeB="+timeB);
//
//        DynamicArray arr  = new DynamicArray();
//        for (int i =0;i<10;i++){
//            arr.addLast(i+1);
//        }
//        ArrayQueue<Integer> aQueue = new ArrayQueue();
//        for (int i =0;i<19;i++){
//            aQueue.enqueue(i);
//        }
//
//        LoopQueue<Integer> lQueue = new LoopQueue<>(20);
//
//
//        LoopQueueTwo<Integer> loopQueueTwo = new LoopQueueTwo<>(20);
//
//        for (int i =0;i<20;i++){
//            System.out.println("enqueue("+i+"):");
//            loopQueueTwo.enqueue(i);
////            if (i%3 ==2){
////                loopQueueTwo.enqueue(i);
////            }
//            if (i%4 ==1){
//                loopQueueTwo.dequeue();
//            }
//        }
//
//        System.out.println(loopQueueTwo.toString());
////
////        System.out.println(arr);
////        arr.add(10,100);
////        System.out.println(arr);
////        arr.addFirst(-1);
////        System.out.println(arr);
////        System.out.println(loopQueueTwo.toString());

    }
}
