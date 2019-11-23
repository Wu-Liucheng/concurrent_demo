package xyz.somedefinitons;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    // volatile 保证数据可见性 不保证原子性 禁止指令重排
    volatile int number = 0;
    public void addTo60(){
        this.number = 60;
    }

    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}
public class VolatileDemo {
    public static void main(String[] args) {
//        makeSureVisible();
        MyData myData = new MyData();
        for(int i = 0; i < 20; i++){
            new Thread(()->{
                for (int j = 0; j < 1000; j++)
                {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },"Thread "+i).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(myData.number+" ** "+myData.atomicInteger.get());
    }

    private static void makeSureVisible() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t updated number value:"+myData.number);
        },"AAA").start();

        while (myData.number==0){

        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }
}
