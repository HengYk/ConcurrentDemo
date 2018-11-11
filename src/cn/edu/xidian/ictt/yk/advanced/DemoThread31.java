package cn.edu.xidian.ictt.yk.advanced;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by heart_sunny on 2018/11/3
 */
public class DemoThread31 {

    public static void testAdd() {

        LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<>(3);

        lbq.add(1);
        lbq.offer(2);

        try {
            lbq.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // lbq.add(8); // 如果队列满了，则抛出异常。
        System.out.println(lbq); // [1, 2, 3]

        lbq.offer(4); // 如果队列满了，不阻塞，不抛出异常。
        System.out.println(lbq); // [1, 2, 3]

        try {
            //可设置最大阻塞时间timeout。超时之后如果队列还是满的，不阻塞，不抛出异常。
            lbq.offer(5, 2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lbq); // [1, 2, 3]

        try {
            lbq.put(6); // 如果队列是满的，则永远阻塞下去。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lbq); // 无输出...

        System.out.println("over"); // 无输出...
    }
    
    public static void testTakeOne() {

        LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<>();

        lbq.add(2);
        lbq.add(4);

        System.out.println(lbq); // [2, 4]

        System.out.println(lbq.peek()); // 2 获取头部元素不移除。
        System.out.println(lbq); // [2, 4]

        System.out.println(lbq.poll()); // 2 获取头部元素并移除。
        System.out.println(lbq); // [4]

        try {
            System.out.println(lbq.take()); // 4 获取头部元素并移除。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lbq); // []

        System.out.println(lbq.poll()); // null 如果队列为空，不阻塞，不抛异常。
        System.out.println(lbq); // []

        try {
            // 可设置阻塞时间timeout。超时之后如果队列依然为空，不阻塞，不抛异常。
            System.out.println(lbq.poll(2, TimeUnit.SECONDS)); // null
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lbq); // []

        try {
            System.out.println(lbq.take()); // 如果队列为空，则永远阻塞，不抛异常。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lbq); // 无输出...

        System.out.println("over"); // 无输出...
    }
    
    public static void testTakeTwo() {

        LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<>();

        lbq.add(1);
        lbq.add(2);

        ArrayList<Integer> al = new ArrayList<>();
        lbq.drainTo(al, 1); // 取abq中指定个数的元素到al中，并从abq中移除。

        System.out.println(al); // [1]
        System.out.println(lbq); // [2]
    }
    
    public static void testTakeThree() {

        LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<>();

        lbq.add(1);
        lbq.add(2);

        ArrayList<Integer> al = new ArrayList<>();
        lbq.drainTo(al); // 取abq中全部元素到al中，并从abq中移除。
        System.out.println(lbq); // []
        System.out.println(al); // [1, 2]

        ArrayList<Integer> arrayList = new ArrayList<>();
        lbq.drainTo(arrayList); // 当队列为空时，不阻塞，不抛异常。
        System.out.println(lbq); // []
        System.out.println(arrayList); // []
    }
    
    public static void main(String[] args) {
        // testAdd();
        // testTakeOne();
        // testTakeTwo();
        testTakeThree();
    }
}
