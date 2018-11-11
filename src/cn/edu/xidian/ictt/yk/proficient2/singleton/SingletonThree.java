package cn.edu.xidian.ictt.yk.proficient2.singleton;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class SingletonThree {

    private static class Singleton{
        private static SingletonThree SingletonThree = new SingletonThree();
    }

    private SingletonThree() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static SingletonThree getInstance() {
        return Singleton.SingletonThree;
    }

    @Override
    public String toString() {
        return "" + this.hashCode();
    }
}
