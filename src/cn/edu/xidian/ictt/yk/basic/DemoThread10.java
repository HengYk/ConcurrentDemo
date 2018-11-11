package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/5
 */
class Person {

    private String name;
    private int age;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class DemoThread10 {

    private Person per = new Person();

    public void changeUser(String name, int age) {

        // 其他对象锁
        synchronized (per) {
            System.out.println("Start:" + Thread.currentThread().getName() + "-" + per.getName() + "-" + per.getAge());

            //修改锁对象的引用，引发线程安全问题，“此处”得到证实。
            //per = new Person();

            //只修改锁对象的属性，不会引发线程安全问题
            per.setName(name);
            per.setAge(age);
            System.out.println("Modified:" + Thread.currentThread().getName() + "-" + per.getName() + "-" + per.getAge());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End:" + Thread.currentThread().getName() + "-" + per.getName() + "-" + per.getAge());
        }
    }

    public static void main(String[] args) {

        DemoThread10 m = new DemoThread10();

        new Thread(new Runnable() {
            @Override
            public void run() {
                m.changeUser("yk", 99);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                m.changeUser("yk-1", 100);
            }
        }).start();
    }
}
