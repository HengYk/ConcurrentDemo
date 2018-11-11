package cn.edu.xidian.ictt.yk.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heart_sunny on 2018/11/2
 */
public class DemoThread13 {

    /**
     * 共享内存的可见性
     */
    private List<String> list = new ArrayList<>();
    private volatile boolean canGet = false;

    private void put() {

        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list.add("A");
            System.out.println("线程" + Thread.currentThread().getName() + "添加第" + i + "个元素");

            if (i == 5) {
                canGet = true;
                System.out.println("线程" + Thread.currentThread().getName() + "发出通知");
            }
        }
    }

    private void get() {

        while (true) {
            if (canGet) {
                for (String s : list) {
                    System.out.println("线程" + Thread.currentThread().getName() + "获取元素：" + s);
                }
                break;
            }
        }
    }

    public static void main(String[] args) {

        DemoThread13 d13 = new DemoThread13();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d13.put();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d13.get();
            }
        }).start();
    }

    /*
    -----------属性canGet添加volatile(主程序可终止)-----------
    线程Thread-0添加第0个元素
    线程Thread-0添加第1个元素
    线程Thread-0添加第2个元素
    线程Thread-0添加第3个元素
    线程Thread-0添加第4个元素
    线程Thread-0添加第5个元素
    线程Thread-0发出通知
    线程Thread-1获取元素：A
    线程Thread-1获取元素：A
    线程Thread-1获取元素：A
    线程Thread-1获取元素：A
    线程Thread-1获取元素：A
    线程Thread-1获取元素：A
    线程Thread-0添加第6个元素
    线程Thread-0添加第7个元素
    线程Thread-0添加第8个元素
    线程Thread-0添加第9个元素

    -----------属性canGet不添加volatile(Thread-1不终止)-----------
    线程Thread-0添加第0个元素
    线程Thread-0添加第1个元素
    线程Thread-0添加第2个元素
    线程Thread-0添加第3个元素
    线程Thread-0添加第4个元素
    线程Thread-0添加第5个元素
    线程Thread-0发出通知
    线程Thread-0添加第6个元素
    线程Thread-0添加第7个元素
    线程Thread-0添加第8个元素
    线程Thread-0添加第9个元素

    -----------结论-----------
    volatile可以保证共享内存的可见性。
     */
}
