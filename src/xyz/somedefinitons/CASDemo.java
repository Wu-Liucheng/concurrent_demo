package xyz.somedefinitons;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 10));
        System.out.println(atomicInteger.compareAndSet(10, 3));
        System.out.println(atomicInteger.compareAndSet(10, 20));
    }
}
