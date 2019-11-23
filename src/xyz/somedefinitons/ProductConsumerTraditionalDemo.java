package xyz.somedefinitons;

//生产者消费者模式，reentrant lock实现 线程操纵资源类

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    public int number = 0;
    private ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();
    public void increment(){

        try {
            reentrantLock.lock();
            while(number!=0){
                condition.await();
            }
            number++;
            System.out.println("++");
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
    public void decrement(){
        try{
            reentrantLock.lock();
            while (number==0){
                condition.await();
            }
            number--;
            System.out.println("--");
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
}
public class ProductConsumerTraditionalDemo {
    public static void main(String[] args) {
        ShareData data = new ShareData();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                data.increment();
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                data.decrement();
            }).start();
        }
    }
}
