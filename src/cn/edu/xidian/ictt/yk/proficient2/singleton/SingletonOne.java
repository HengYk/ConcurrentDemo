package cn.edu.xidian.ictt.yk.proficient2.singleton;

/**
 * Created by heart_sunny on 2018/6/10
 */
public class SingletonOne {

    private static SingletonOne singletonOne = new SingletonOne();

    private SingletonOne() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static SingletonOne getInstance() {
        return singletonOne;
    }

    @Override
    public String toString() {
        return "" + this.hashCode();
    }
}
