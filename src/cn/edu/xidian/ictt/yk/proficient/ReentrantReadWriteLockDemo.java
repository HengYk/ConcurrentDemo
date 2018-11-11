package cn.edu.xidian.ictt.yk.proficient;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.*;

public class ReentrantReadWriteLockDemo {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private ReadLock readLock = lock.readLock();
    private WriteLock writeLock = lock.writeLock();

    public void read() {

        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "read...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "read exit...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {

        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "write...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "write exit...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.read();
//            }
//        }, "t1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.read();
//            }
//        }, "t2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.write();
            }
        }, "t3").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.write();
            }
        }, "t4").start();
    }
}
