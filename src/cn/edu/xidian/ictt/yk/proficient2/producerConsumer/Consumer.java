package cn.edu.xidian.ictt.yk.proficient2.producerConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class Consumer implements Runnable {

    private String name;
    private BlockingQueue<Data> queue;

    public Consumer(String name, BlockingQueue<Data> queue) {
        this.name = name;
        this.queue = queue;
    }

    private static Random r = new Random();

    @Override
    public void run() {
        while (true) {
            try {
                Data data = this.queue.poll(5, TimeUnit.SECONDS);

                if (data == null) {
                    System.out.println("当前消费：" + name + "， 超过5s无法获取数据，退出监听");
                    return;
                }

                Thread.sleep(r.nextInt(1000));

                System.out.println("当前消费者：" + name + ", 消费成功，消费数据为id：" + data.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
