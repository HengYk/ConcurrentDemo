package cn.edu.xidian.ictt.yk.proficient;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemoThree {

    private static ReentrantLock lock = new ReentrantLock();

    public static void runOne () {

        try {
            lock.lock();
            System.out.println(lock.getHoldCount());
            runTwo();
        } finally {
            System.out.println("release lock 1");
            lock.unlock();
        }
    }

    public static void runTwo () {

        try {
            lock.lock();
            System.out.println(lock.getHoldCount());
        } finally {
            System.out.println("release lock 2");
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
    }

    /*
    1
    2
    release lock 2
    release lock 1
     */
}
