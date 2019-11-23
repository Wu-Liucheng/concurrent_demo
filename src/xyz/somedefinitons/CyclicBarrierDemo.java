package xyz.somedefinitons;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("收集到七颗龙珠，召唤神龙！");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                System.out.println("收集到第"+Thread.currentThread().getName()+"颗龙珠.");
                try{
                    cyclicBarrier.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
