package xyz.somedefinitons;

import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        try{
            readWriteLock.writeLock().lock();
            map.put(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
    public Object get(String key){
        try{
            readWriteLock.readLock().lock();
            return map.get(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}
public class ReadWriteDemo {

}
