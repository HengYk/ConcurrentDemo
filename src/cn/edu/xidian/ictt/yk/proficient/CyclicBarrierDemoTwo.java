package cn.edu.xidian.ictt.yk.proficient;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by heart_sunny on 2018/5/26
 */
class Worker implements Runnable {

    private String name;
    private CyclicBarrier cyclicBarrier;

    public Worker(String name, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        try {
            System.out.println(name + "运行完毕");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierDemoTwo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        for (int i = 0; i < 9; i ++) {
            new Thread(new Worker("worker" + i, cyclicBarrier)).start();
        }

        System.out.println("主线程开始等待..................");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("主线程开始工作..................");
    }

    /*
    worker0运行完毕
    worker1运行完毕
    主线程开始等待..................
    worker2运行完毕
    worker3运行完毕
    worker4运行完毕
    worker5运行完毕
    worker6运行完毕
    worker7运行完毕
    worker8运行完毕
    主线程开始工作..................
     */
}
