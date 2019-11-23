package xyz.somedefinitons;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//线程顺序执行，精确唤醒
class ShareResource{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public void printA(){
        lock.lock();
        try {
            while(number!=1){
                condition1.await();
            }
            for(int i = 0;i<3;i++){
                System.out.println("AAA");
            }
            number = 2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            while(number!=2){
                condition2.await();
            }
            for(int i = 0;i<3;i++){
                System.out.println("BBB");
            }
            number = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while(number!=3){
                condition3.await();
            }
            for(int i = 0;i<3;i++){
                System.out.println("CCC");
            }
            number = 1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class NotifyAccurate {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                shareResource.printA();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                shareResource.printB();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                shareResource.printC();
            }
        }).start();
    }
}
