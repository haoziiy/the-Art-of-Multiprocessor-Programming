package concurrency.JavaConcurrency;

/**
 * Created by sherry on 2017/5/12.
 */
public class BasicThread {
    public static void main(String[] args){
//        Thread t = new Thread(new Liftoff());
//        t.start();

        for (int i = 0 ;i < 5;i++)

            new Thread(new Liftoff()).start();

        System.out.println("Waiting for Liftoff");
    }
}
