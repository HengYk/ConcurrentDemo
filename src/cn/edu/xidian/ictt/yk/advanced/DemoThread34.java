package cn.edu.xidian.ictt.yk.advanced;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by heart_sunny on 2018/11/4
 */
class User implements Delayed {

    private int id;
    private String name;
    private long endTime;

    public User(int id, String name, long endTime) {
        this.id = id;
        this.name = name;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Delayed o) {

        User user = (User) o;

        if (this.getEndTime() > user.getEndTime()) {
            return 1;
        } else if (this.getEndTime() < user.getEndTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.endTime - System.currentTimeMillis();
    }
}

/**
 * 到时间后强制用户下线功能模拟
 */
public class DemoThread34 {

    DelayQueue<User> dq = new DelayQueue<>();

    public void login(User user) {
        dq.add(user);
        System.out.println("login: " + user.getId() + "-" + user.getName() + "-" + user.getEndTime());
    }

    public void logout() {
        try {
            User user = dq.take(); // 此处不能使用dq.poll()[非阻塞式方法]
            System.out.println("logout: " + user.getId() + "-" + user.getName() + "-" + user.getEndTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int onlineSize() {
        return dq.size();
    }

    public DelayQueue<User> getUsers() {
        return dq;
    }

    public static void main(String[] args) {

        DemoThread34 demoThread34 = new DemoThread34();

        demoThread34.login(new User(1, "a", 3000 + System.currentTimeMillis()));
        demoThread34.login(new User(2, "b", 1000 + System.currentTimeMillis()));
        demoThread34.login(new User(3, "c", 2000 + System.currentTimeMillis()));

        System.out.println(demoThread34.getUsers());

        while (true) {
            demoThread34.logout();
            System.out.println(demoThread34.getUsers());
            if (demoThread34.onlineSize() == 0) {
                break;
            }
        }
    }

    /*
    login: 1-a-1541303057689
    login: 2-b-1541303055689
    login: 3-c-1541303056691
    [b, a, c]
    logout: 2-b-1541303055689
    [c, a]
    logout: 3-c-1541303056691
    [a]
    logout: 1-a-1541303057689
    []
     */
}
