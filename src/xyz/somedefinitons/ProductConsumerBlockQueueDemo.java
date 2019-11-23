package xyz.somedefinitons;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProductConsumerBlockQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        new Thread(()->{
            String product;
            while (true){
                product = UUID.randomUUID().toString().substring(0,4);
                if(queue.offer(product)==true){
                    System.out.println("生产"+product);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            String res;
            while (true){
                try {
                    res = queue.take();
                    System.out.println("消费"+res);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
