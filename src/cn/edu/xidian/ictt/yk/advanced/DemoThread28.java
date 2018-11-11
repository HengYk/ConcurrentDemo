package cn.edu.xidian.ictt.yk.advanced;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by heart_sunny on 2018/11/3
 */
public class DemoThread28 {

    public static void testOne() {

        CopyOnWriteArrayList<Integer> al = new CopyOnWriteArrayList<>();

        al.add(1);
        al.add(4);
        al.add(2);
        al.addIfAbsent(3);
        al.addIfAbsent(5);

        System.out.println(al); // [1, 4, 2, 3, 5]

        al.add(2);

        System.out.println(al); // [1, 4, 2, 3, 5, 2]
    }

    public static void testTwo() {

        CopyOnWriteArraySet<Integer> as = new CopyOnWriteArraySet<>();

        as.add(1);
        as.add(4);
        as.add(2);
        as.add(3);

        System.out.println(as); // [1, 4, 2, 3]

        as.add(2);
        System.out.println(as); // [1, 4, 2, 3]
    }

    public static void main(String[] args) {
        // testOne();
        testTwo();
    }
}
