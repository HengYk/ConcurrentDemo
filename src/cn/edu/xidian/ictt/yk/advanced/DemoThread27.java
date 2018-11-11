package cn.edu.xidian.ictt.yk.advanced;

import java.util.Collections;
import java.util.Hashtable;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by heart_sunny on 2018/11/3
 */

public class DemoThread27 {

    /**
     * 通过并发下的运行时间对比ConcurrentHashMap和Hashtable的性能
     */
    public static void testMapOne() {

        // Hashtable<String, Integer> hash = new Hashtable<>();

        ConcurrentHashMap<String, Integer> hash = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    for (int i = 0; i < 1000000; i++) {
                        hash.put("k" + i, i);
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + (System.currentTimeMillis() - start));
                }
            }).start();
        }
    }

    public static void testSkipListMapOne() {

        // SortedMap<String, Integer> skipMap = new TreeMap<>();

        // 性能低，线程安全
        // SortedMap<String, Integer> skipMap = Collections.synchronizedSortedMap(new TreeMap<>());

        // 性能高，线程安全
        ConcurrentSkipListMap<String, Integer> skipMap = new ConcurrentSkipListMap<>();

        for (int i = 0; i < 10; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    for (int i = 0; i < 1000; i++) {
                        try {
                            Thread.sleep(0);
                            skipMap.put("k" + i, i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + (System.currentTimeMillis() - start));
                    // System.out.println(skipMap);
                }
            }).start();
        }
    }

    /**
     * ConcurrentHashMap用法
     */
    public static void testMapTwo() {

        ConcurrentHashMap<String, Integer> hashMap = new ConcurrentHashMap<>();

        hashMap.put("d", 2);
        hashMap.put("c", 4);
        hashMap.put("e", 2);

        hashMap.put("e", 3);
        System.out.println(hashMap); // {c=4, d=2, e=3}

        hashMap.putIfAbsent("d1", 1);
        System.out.println(hashMap); // {c=4, d=2, e=3, d1=1}
    }

    /**
     * ConcurrentSkipListMap用法
     */
    public static void testSkipListMapTwo() {

        ConcurrentSkipListMap<String, Integer> skipListMap = new ConcurrentSkipListMap<>();

        skipListMap.put("d", 2);
        skipListMap.put("c", 4);
        skipListMap.put("e", 2);

        skipListMap.put("e", 3);
        System.out.println(skipListMap); // {c=4, d=2, e=3}

        skipListMap.putIfAbsent("d1", 1);
        System.out.println(skipListMap); // {c=4, d=2, d1=1, e=3}
    }

    public static void main(String[] args) {
        // testMapOne();
        // testSkipListMapOne();
        testMapTwo();
        // testSkipListMapTwo();
    }

    /*
    -----------古老的并发容器Hashtable的执行时间-----------
    Thread-6: 4249
    Thread-4: 4466
    Thread-3: 4582
    Thread-2: 4647
    Thread-9: 4575
    Thread-5: 4576
    Thread-8: 4593
    Thread-0: 4710
    Thread-1: 4726
    Thread-7: 4638

    -----------改进的并发容器ConcurrentHashMap的执行时间-----------
    Thread-0: 2582
    Thread-7: 2703
    Thread-9: 2763
    Thread-4: 2833
    Thread-3: 2900
    Thread-5: 2883
    Thread-1: 3010
    Thread-2: 3032
    Thread-8: 2901
    Thread-6: 2928

    Thread-0: 4313
    Thread-2: 4312
    Thread-3: 4270
    Thread-1: 4448
    Thread-9: 4259
    Thread-5: 4225
    Thread-7: 4357
    Thread-6: 4358
    Thread-8: 4394
    Thread-4: 4366

    -----------结论1-----------
    执行结果表明，ConcurrentHashMap大多数情况下会比Hashtable优秀一些。

    -----------使用SortedMap-----------
    Exception in thread "Thread-1" java.lang.NullPointerException
    Thread-6: 79
    Thread-9: 79
    Thread-7: 79
    Thread-2: 81
    Thread-8: 80
    Thread-4: 81
    Thread-5: 82
    Thread-3: 82
    Thread-0: 84

    -----------使用Collections.synchronizedSortedMap(SortedMap)-----------
    Thread-0: 166
    Thread-8: 157
    Thread-9: 157
    Thread-1: 166
    Thread-2: 165
    Thread-3: 166
    Thread-5: 166
    Thread-6: 165
    Thread-7: 165
    Thread-4: 166

    -----------使用ConcurrentSkipListMap-----------
    Thread-3: 71
    Thread-1: 74
    Thread-7: 71
    Thread-5: 72
    Thread-2: 74
    Thread-8: 72
    Thread-9: 72
    Thread-6: 73
    Thread-0: 77
    Thread-4: 92

    -----------结论2-----------
    直接使用SortedMap性能高但线程不安全；
    添加锁Collections.synchronizedSortedMap(SortedMap)线程安全但性能降低；
    使用使用ConcurrentSkipListMap既保证线程安全又提高了性能。
     */
}
