package cn.edu.xidian.ictt.yk.proficient;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private static ReentrantLock lock = new ReentrantLock();

    public static void runOne() {

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "runOne...");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "runOne finished...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void runTwo() {

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "runTwo...");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOne();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                runTwo();
            }
        }, "t2").start();
    }

    /*
    t1runOne...
    t1runOne finished...
    t2runTwo...
     */
}
