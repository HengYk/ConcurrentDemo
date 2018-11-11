package cn.edu.xidian.ictt.yk.advanced;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by heart_sunny on 2018/11/3
 */
public class DemoThread29 {

    public static void main(String[] args) {

        ConcurrentLinkedQueue<Integer> clq = new ConcurrentLinkedQueue<>();

        clq.add(1);
        clq.add(2);

        clq.offer(3);
        clq.offer(4);

        System.out.println(clq); // [1, 2, 3, 4]

        System.out.println(clq.peek()); // 1
        System.out.println(clq.size()); // 4

        System.out.println(clq.poll()); // 1
        System.out.println(clq.size()); // 3

        System.out.println(clq.poll()); // 2
        System.out.println(clq.poll()); // 3
        System.out.println(clq.poll()); // 4
        System.out.println(clq.size()); // 0

        System.out.println(clq.peek()); // null
        System.out.println(clq.poll()); // null
    }
}
