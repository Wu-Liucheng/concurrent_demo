package xyz.somedefinitons;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask实现了Runnable接口，并且实现了需要一个Callable类型的构造函数，不会被多个线程复用
        //适配器？？
        FutureTask task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return 1024;
            }
        });
        new Thread(task).start();
        System.out.println(task.get());//阻塞的方法
    }
}
