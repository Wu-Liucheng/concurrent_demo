package xyz.somedefinitons;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

//自旋锁
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
//        System.out.println(thread.getName());
        while (!atomicReference.compareAndSet(null,thread)){
            System.out.println(thread.getName()+"尝试获取锁");
        }
    }
    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"A").start();
        try{
            TimeUnit.MILLISECONDS.sleep(300);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnlock();
        },"B").start();
    }
}
