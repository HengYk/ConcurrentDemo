package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/5
 */
class NotifyAllCase {

    public synchronized void runOne() {
        System.out.println("进入runOne方法");
        //this.notify();
        this.notifyAll();
        System.out.println("runOne执行完毕");
    }

    public synchronized void runTwo() {
        System.out.println("进入runTwo方法");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        this.notify();
//        System.out.println("runTwo发起通知");
        System.out.println("runTwo执行完毕");
    }

    public synchronized void runThree() {
        System.out.println("进入runThree方法");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("runThree执行完毕");
    }
}

public class DemoThread22 {

    public static void main(String[] args) {

        NotifyAllCase nac = new NotifyAllCase();
        new Thread(new Runnable() {
            @Override
            public void run() {
                nac.runTwo();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                nac.runThree();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                nac.runOne();
            }
        }).start();
    }
}
