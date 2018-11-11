package cn.edu.xidian.ictt.yk.proficient2.future;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class FutureData implements Data {

    private RealData realData = null;
    private boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) return;

        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        if (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }
}
