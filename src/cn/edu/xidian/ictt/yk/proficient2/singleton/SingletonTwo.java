package cn.edu.xidian.ictt.yk.proficient2.singleton;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class SingletonTwo {

    private static SingletonTwo singletonTwo = null;

    private SingletonTwo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 线程安全 + 性能优化
    public /*synchronized*/ static SingletonTwo getInstance() {
        if (singletonTwo == null) {
            synchronized (SingletonTwo.class) {
                if (singletonTwo == null) {
                    singletonTwo = new SingletonTwo();
                }
            }
        }
        return singletonTwo;

    }

    @Override
    public String toString() {
        return "" + this.hashCode();
    }
}
