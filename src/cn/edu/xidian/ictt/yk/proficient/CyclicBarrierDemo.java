package cn.edu.xidian.ictt.yk.proficient;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by heart_sunny on 2018/5/26
 */
class Runner implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private String name;

    public Runner(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    @Override
    public void run() {

        try {
            long time = 1000 * (new Random()).nextInt(8);
            Thread.sleep(time);
            System.out.println(name + "::准备好了::" + time);
            //cyclicBarrier.await();
            cyclicBarrier.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println(name + "::线程中断");
            return;
        } catch (BrokenBarrierException e) {
            System.out.println(name + "::Barrier异常");
            return;
        } catch (TimeoutException e) {
            System.out.println(name + "::超时异常");
        }

        System.out.println(name + "::跑呀...");
    }
}

public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new Runner(cyclicBarrier, "t1"));
        executorService.submit(new Runner(cyclicBarrier, "t2"));
        executorService.submit(new Runner(cyclicBarrier, "t3"));

        executorService.shutdown();
    }

    /*
    -------------结果可能是这样的-------------
    t1::准备好了::1000
    t2::准备好了::4000
    t3::准备好了::4000
    t3::跑呀...
    t1::跑呀...
    t2::跑呀...

    -------------结果也可能是这样的-------------
    t3::准备好了::0
    t3::超时异常
    t3::跑呀...
    t1::准备好了::6000
    t1::Barrier异常
    t2::准备好了::7000
    t2::Barrier异常

    注意比较上述结果中时间的差值，如果最长准备时间比最短准备时间多3秒，则抛出异常TimeoutException和BrokenBarrierException。
     */
}
