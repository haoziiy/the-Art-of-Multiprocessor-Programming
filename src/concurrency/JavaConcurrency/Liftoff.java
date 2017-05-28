package concurrency.JavaConcurrency;

/**
 * Created by sherry on 2017/5/12.
 */
public class Liftoff implements Runnable{
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public Liftoff(){
    }
    public Liftoff(int countDown){
        this.countDown = countDown;
    }

    public String status() {
        return "#Thread id: " + id + ", time: " + (countDown > 0 ? countDown : 0);
    }

    public void run() {
            while (countDown-- > 0){
            System.out.println(status());
            Thread.yield();
        }
    }
}
