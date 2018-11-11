package cn.edu.xidian.ictt.yk.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heart_sunny on 2018/11/5
 */
class NotWaitNotifyCase {

    private volatile List<String> list = new ArrayList<>();
    private volatile boolean canGet = false;

    public void set() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-" + i);
            list.add("A");
            if (i == 5) {
                canGet = true;
                System.out.println(Thread.currentThread().getName() + "发出通知");
            }
        }
    }

    public void get() {
        while (true) {
            if (canGet) {
                for (String l : list) {
                    System.out.println(l);
                }
                break;
            }
        }
    }
}

class WaitNotifyCase {

    private List<String> list = new ArrayList<>();
    private final Object obj = new Object();

    public void set() {
        synchronized (obj) {
            for (int i = 0; i < 10; i++) {

                System.out.println(Thread.currentThread().getName() + "-" + i);
                list.add("A");

                if (list.size() == 5) {
                    //注意区别：notify()触发后不释放锁
                    obj.notify();
                    System.out.println(Thread.currentThread().getName() + "发出通知");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void get() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + "发起等待");

            try {
                //注意区别：wait()触发后释放锁
                obj.wait();
                System.out.println(Thread.currentThread().getName() + "被动唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (String l : list) {
                System.out.println(l);
            }
        }
    }
}

public class DemoThread17 {

    public static void main(String[] args) {

        NotWaitNotifyCase nwnc = new NotWaitNotifyCase();
        //WaitNotifyCase wnc = new WaitNotifyCase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                nwnc.get();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                nwnc.set();
            }
        }).start();
    }
}
