package cn.edu.xidian.ictt.yk.proficient2.producerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class Main {

    public static void main(String[] args) throws Exception{

        BlockingQueue<Data> queue = new LinkedBlockingQueue<>(1);

        Producer p1 = new Producer("p1", queue);
        Producer p2 = new Producer("p2", queue);
        Producer p3 = new Producer("p3", queue);

        Consumer c1 = new Consumer("c1", queue);
//        Consumer c2 = new Consumer("c2", queue);
//        Consumer c3 = new Consumer("c3", queue);
//        Consumer c4 = new Consumer("c4", queue);
//        Consumer c5 = new Consumer("c5", queue);

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        executorService.execute(p1);
        executorService.execute(p2);
        executorService.execute(p3);

        executorService.execute(c1);
//        executorService.execute(c2);
//        executorService.execute(c3);
//        executorService.execute(c4);
//        executorService.execute(c5);

        Thread.sleep(3000);

        p1.stop();
        p2.stop();
        p3.stop();

        executorService.shutdown();
    }
}
