package xyz.somedefinitons;


import java.util.concurrent.locks.ReentrantLock;

//java可重入锁
class Phone implements Runnable{
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+" sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+" sendEmail");
    }
    ReentrantLock reentrantLock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    public void get(){
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+" get()");
            set();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
    public void set(){
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+" set()");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
}
public class ReentrantDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        /*new Thread(()->{
            phone.sendSMS();
        },"t1").start();
        new Thread(()->{
            phone.sendSMS();
        },"t2").start();*/

        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();
    }
}
