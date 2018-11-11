package cn.edu.xidian.ictt.yk.advanced;

import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by heart_sunny on 2018/11/4
 */
class Demo implements Comparable<Demo> {

    private int id;
    private String name;

    public Demo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Demo o) {
        if (this.id > o.id) {
            return 1;
        } else if (this.id < o.id) {
            return -1;
        } else {
            return 0;
        }
    }
}

public class DemoThread33 {

    /**
     * 队列中的数据不是按照顺序排列的
     */
    public static void testAdd() {

        PriorityBlockingQueue<Demo> pq = new PriorityBlockingQueue<>(); // 默认容量11

        pq.add(new Demo(3, "a"));
        pq.offer(new Demo(1, "b"));
        pq.put(new Demo(2, "c"));
        pq.offer(new Demo(4, "d"), 2, TimeUnit.SECONDS);

        System.out.println(pq); // [Demo{id=1, name='b'}, Demo{id=3, name='a'}, Demo{id=2, name='c'}, Demo{id=4, name='d'}]
    }

    /**
     * 队列中的元素按照顺序被取走
     */
    public static void testTake1() {

        PriorityBlockingQueue<Demo> pq = new PriorityBlockingQueue<>();

        pq.add(new Demo(3, "a"));
        pq.offer(new Demo(1, "b"));
        pq.put(new Demo(2, "c"));
        pq.offer(new Demo(4, "d"), 2, TimeUnit.SECONDS);

        try {
            System.out.println(pq.poll()); // Demo{id=1, name='b'}
            System.out.println(pq.poll(2, TimeUnit.SECONDS)); // Demo{id=2, name='c'}
            System.out.println(pq.take()); // Demo{id=3, name='a'}
            System.out.println(pq.take()); // Demo{id=4, name='d'}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testTake2() {

        PriorityBlockingQueue<Demo> pbq = new PriorityBlockingQueue<>();

        pbq.add(new Demo(3, "a"));
        pbq.add(new Demo(1, "b"));

        System.out.println(pbq); // [Demo{id=1, name='b'}, Demo{id=3, name='a'}]

        System.out.println(pbq.peek()); // Demo{id=1, name='b'}
        System.out.println(pbq); // [Demo{id=1, name='b'}, Demo{id=3, name='a'}]

        System.out.println(pbq.poll()); // Demo{id=1, name='b'}
        System.out.println(pbq);// [Demo{id=3, name='a'}]

        try {
            System.out.println(pbq.take()); // Demo{id=3, name='a'}
            System.out.println(pbq); // []
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(pbq.peek()); // null
        System.out.println(pbq.poll()); // null
        try {
            System.out.println(pbq.poll(2, TimeUnit.SECONDS)); //null
            System.out.println(pbq.take()); // 阻塞...
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("over"); // 无输出...
    }

    public static void testTake3() {

        PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>();

        pbq.add(3);
        pbq.add(2);
        pbq.add(3);
        pbq.add(1);
        pbq.add(5);

        ArrayList<Integer> arrayList = new ArrayList<>();
        pbq.drainTo(arrayList, 2);
        System.out.println(arrayList); // [1, 2]
        System.out.println(pbq); // [3, 5, 3]

        Object[] list = arrayList.toArray();
        Arrays.sort(list); // [1, 2]
        System.out.println(Arrays.toString(list));
    }

    public static void testTake4() {

        PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>();

        pbq.add(3);
        pbq.add(2);
        pbq.add(3);
        pbq.add(1);
        pbq.add(5);

        ArrayList<Integer> arrayList = new ArrayList<>();
        pbq.drainTo(arrayList);

        ArrayList<Integer> list = new ArrayList<>();
        pbq.drainTo(list);

        System.out.println(list); // []
        System.out.println(pbq); // []
    }

    public static void main(String[] args) {
        // testAdd();
        testTake1();
        // testTake2();
        // testTake3();
        // testTake4();
    }
}
