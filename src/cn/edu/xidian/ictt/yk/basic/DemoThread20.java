package cn.edu.xidian.ictt.yk.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heart_sunny on 2018/11/5
 */
class MQueue {

    private List<String> list = new ArrayList<>();
    private int maxSize;
    private final Object lock = new Object();

    public MQueue(int maxSize) {
        this.maxSize = maxSize;
        System.out.println("阻塞队列的最大长度为" + maxSize);
    }

    public void put(String element) {
        synchronized (lock) {
            if (list.size() == maxSize) {
                System.out.println(Thread.currentThread().getName() + "-队列已满");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(element);
            System.out.println(Thread.currentThread().getName() + "-队列中加入了" + element);
            lock.notifyAll();
            //System.out.println(Thread.currentThread().getName() + "-发起通知");
        }
    }

    public String take() {
        synchronized (lock) {
            if (list.size() == 0) {
                System.out.println(Thread.currentThread().getName() + "-队列为空");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String s = list.remove(0);
            System.out.println(Thread.currentThread().getName() + "-队列中取出了" + s);
            lock.notifyAll();
            //System.out.println(Thread.currentThread().getName() + "-发起通知");
            return s;
        }
    }
}

public class DemoThread20 {

    public static void main(String[] args) {

        MQueue mQueue = new MQueue(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mQueue.put("1");
                mQueue.put("2");
                mQueue.put("3");
                mQueue.put("4");
                mQueue.put("5");
                mQueue.put("6");
                mQueue.put("7");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mQueue.put("21");
                mQueue.put("22");
                mQueue.put("23");
                mQueue.put("24");
                mQueue.put("25");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mQueue.take();
                mQueue.take();
                mQueue.take();
                mQueue.take();
                mQueue.take();
                mQueue.take();
                mQueue.take();
                mQueue.take();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mQueue.take();
                mQueue.take();
                mQueue.take();
                mQueue.take();
                mQueue.take();
            }
        }).start();
    }
}
