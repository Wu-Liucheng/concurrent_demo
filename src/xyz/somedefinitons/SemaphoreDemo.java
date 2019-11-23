package xyz.somedefinitons;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try{
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"获得信号量");
                    TimeUnit.SECONDS.sleep(3);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"释放信号量");
                }
            },String.valueOf(i)).start();
        }
    }
}
