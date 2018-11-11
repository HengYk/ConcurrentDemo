package cn.edu.xidian.ictt.yk.additional;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by heart_sunny on 2018/5/18
 */
public class Resource {

    private int count = 0;
    private boolean flag = false;

    private Lock lock = new ReentrantLock();
    private Condition prod = lock.newCondition();
    private Condition cons = lock.newCondition();

    public void set() {
        lock.lock();
        try {
            while (flag) {
                try {
                    prod.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "......producer......" + ++count);
            this.flag = true;
            cons.signal();
        } finally {
            lock.unlock();
        }
    }

    public void out() {
        lock.lock();
        try {
            while (!flag) {
                try {
                    cons.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "...consumer..." + count);
            this.flag = false;
            prod.signal();
        } finally {
            lock.unlock();
        }


    }
}
