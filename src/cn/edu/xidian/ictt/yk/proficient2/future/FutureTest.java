package cn.edu.xidian.ictt.yk.proficient2.future;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class FutureTest {

    public static void main(String[] args) {

        Client client = new Client();
        Data data = client.request("!!!");
        System.out.println("1");

        String result = data.getResult();
        System.out.println("2");
        System.out.println(result);
    }
}
