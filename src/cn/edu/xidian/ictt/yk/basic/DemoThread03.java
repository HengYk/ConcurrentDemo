package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/5
 */
class PrintCase {

    public synchronized void printOne() {

        System.out.println(Thread.currentThread().getName() + "-hello");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public /*synchronized*/ void printTwo() {

        System.out.println(Thread.currentThread().getName() + "-hello");
    }
}

public class DemoThread03 {

    public static void main(String[] args) {

        PrintCase pc = new PrintCase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                pc.printOne();
            }
        }, "t1").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                pc.printTwo();
            }
        }, "t2").start();
    }
}
