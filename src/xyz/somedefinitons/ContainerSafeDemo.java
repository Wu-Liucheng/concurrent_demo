package xyz.somedefinitons;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerSafeDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        for(int i = 0; i < 30; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,4));
                System.out.println(list);
            }).start();
        }
    }
}
