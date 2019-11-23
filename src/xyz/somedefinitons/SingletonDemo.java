package xyz.somedefinitons;

public class SingletonDemo {
    private static volatile SingletonDemo singletonDemo = null;
    private SingletonDemo(){
        System.out.println("Constructor");
    }
    //双端检索机制，volatile禁止指令重排
    public static SingletonDemo getInstance(){
        if(singletonDemo == null){
            synchronized (SingletonDemo.class){
                if(singletonDemo == null){
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            new Thread(()->{
                SingletonDemo.getInstance();
            },"第"+i).start();
        }
    }
}
