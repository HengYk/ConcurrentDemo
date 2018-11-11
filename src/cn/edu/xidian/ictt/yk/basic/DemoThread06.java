package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/5
 */
class ParentIns {

    public int i = 10;

    public synchronized void runParent() {
        i--;
        System.out.println("parent-->" + i);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ChildIns extends ParentIns {

    public synchronized void runChild() {

        while (i > 0) {
            i--;
            System.out.println("child--->" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runParent(); // 锁重入
        }
    }
}

public class DemoThread06 {

    public static void main(String[] args) {

        ChildIns ci = new ChildIns();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ci.runChild();
            }
        }).start();
    }

    /*
    child--->9
    parent-->8
    child--->7
    parent-->6
    child--->5
    parent-->4
    child--->3
    parent-->2
    child--->1
    parent-->0
     */
}
