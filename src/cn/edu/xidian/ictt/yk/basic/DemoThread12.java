package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/5
 */
class DeadLockCase {

    private final Object lockOne = new Object();
    private final Object lockTwo = new Object();

    public void executeOne() {
        synchronized (lockOne) {
            System.out.println(Thread.currentThread().getName() + "-lockOne-executeOne");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockTwo) {
                System.out.println(Thread.currentThread().getName() + "-lockTwo-executeOne");
            }
        }
    }

    public void executeTwo() {
        synchronized (lockTwo) {
            System.out.println(Thread.currentThread().getName() + "-lockTwo-executeTwo");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockOne) {
                System.out.println(Thread.currentThread().getName() + "-lockOne-executeTwo");
            }
        }
    }
}

public class DemoThread12 {

    public static void main(String[] args) {

        DeadLockCase dlc = new DeadLockCase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                dlc.executeOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                dlc.executeTwo();
            }
        }).start();
    }

}
