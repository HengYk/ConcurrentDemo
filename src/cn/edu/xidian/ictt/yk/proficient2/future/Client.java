package cn.edu.xidian.ictt.yk.proficient2.future;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class Client {

    public FutureData request(String string) {

        FutureData futureData = new FutureData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(string);
                futureData.setRealData(realData);
            }
        }).start();

        return futureData;
    }
}
