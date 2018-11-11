package cn.edu.xidian.ictt.yk.advanced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by heart_sunny on 2018/11/3
 */
public class DemoThread26 implements Runnable{

    // private static List<String> list = new ArrayList<>();

    private static List<String> list = Collections.synchronizedList(new ArrayList<>());

    private static void add() {
        for (int i = 0; i < 10; i ++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add("A");
        }
    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i ++) {
            es.submit(new DemoThread26());
        }

        es.shutdown();

        while (true) {
            if (es.isTerminated()) {
                System.out.println("线程结束了");
                System.out.println("list.size = " + list.size());
                if (list.size() != 50) {
                    System.out.println("线程不安全");
                } else {
                    System.out.println("线程安全");
                }
                break;
            }
        }
    }

    /*
    -----------不添加Collections.synchronizedList()-----------
    线程结束了
    list.size = 39
    线程不安全

    -----------添加Collections.synchronizedList()-----------
    线程结束了
    list.size = 50
    线程安全

    -----------结论-----------
    添加Collections.synchronizedXXX()可以快速将非线程安全的集合类改造成线程安全的，即并发状态下只能有一个线程访问容器对象，性能很低。
     */
}
