package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/4
 */
class User {

    private String name;
    private String pass;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public /*synchronized*/ void set(String name, String pass) {

        this.name = name;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.pass = pass;

        System.out.println(Thread.currentThread().getName() + "-name-" + this.name + "-pass-" + this.pass);
    }
}

class UserServlet {

    private User user;

    public UserServlet() {
        user = new User("yk", "...");
    }

    public void setUser(String name, String pass) {
        user.set(name, pass);
    }
}

public class DemoThread00 {

    public static void main(String[] args) {

        UserServlet us = new UserServlet();

        new Thread(new Runnable() {
            @Override
            public void run() {
                us.setUser("yk1", "123");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                us.setUser("yk2", "abc");
            }
        }).start();
    }

    /*
    Thread-0-name-yk2-pass-123
    Thread-1-name-yk2-pass-abc
     */
}
