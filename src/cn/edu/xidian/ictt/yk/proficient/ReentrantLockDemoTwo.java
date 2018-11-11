package cn.edu.xidian.ictt.yk.proficient;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemoTwo {

    private static ReentrantLock lock = new ReentrantLock(true);
    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();

    public static void runOne() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "runOne...");
            c1.await();
            System.out.println(Thread.currentThread().getName() + "runOne continue...");
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
            c1.await();
            System.out.println(Thread.currentThread().getName() + "runTwo continue...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void runThree() {

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "runThree...");
            c1.signalAll();
            System.out.println(Thread.currentThread().getName() + "runThree continue...");
        } finally {
            lock.unlock();
        }
    }

    public static void runFour() {

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "runFour...");
            c2.await();
            System.out.println(Thread.currentThread().getName() + "runFour continue...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void runFive() {

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "runFive...");
            c2.signal();
            System.out.println(Thread.currentThread().getName() + "runFive continue...");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                runFour();
            }
        }, "t4").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                runTwo();
            }
        }, "t2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOne();
            }
        }, "t1").start();

        try {
            Thread.sleep(2000);
            System.out.println("--------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                runThree();
            }
        }, "t3").start();

        try {
            Thread.sleep(2000);
            System.out.println("--------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                runFive();
            }
        }, "t5").start();
    }

    /*
    t4runFour...
    t1runOne...
    t2runTwo...
    --------------------------------------
    t3runThree...
    t3runThree continue...
    t1runOne continue...
    t2runTwo continue...
    --------------------------------------
    t5runFive...
    t5runFive continue...
    t4runFour continue...
     */
}
