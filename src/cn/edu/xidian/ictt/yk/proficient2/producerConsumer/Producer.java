package cn.edu.xidian.ictt.yk.proficient2.producerConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class Producer implements Runnable {

    private String name;

    private BlockingQueue<Data> queue;

    // 多线程间是否启动变量，有强制从主存中刷新的功能。即时返回线程的状态。
    private volatile boolean isRunning = true;

    // 数据id生成器
    private static AtomicInteger count = new AtomicInteger();

    // 随机对象
    private static Random random = new Random();

    public Producer(String name, BlockingQueue<Data> queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {

        while (isRunning) {
            try {

                int id = count.incrementAndGet();
                Data data = new Data(Integer.toString(id), "数据" + id);

                Thread.sleep(random.nextInt(1000));

                if (!this.queue.offer(data, 0, TimeUnit.SECONDS)) {
                    // 当生产者生产速度过快而远大于消费者消费的速度，
                    // 同时阻塞队列又不能承载过多的产品，此时会出现“放入队列中超时”
                    System.out.println("生产者" + name + "将" + data.getName() + "放入队列中超时...");
                } else {
                    System.out.println("生产者" + name + ", 创建了数据id为：" + id + ", 并放入了消息队列中");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}
