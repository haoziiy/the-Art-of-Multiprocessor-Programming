package concurrency.ParallelPrefixSum;

/**
 * Created by sherry on 2017/5/28.
 */

public class AddTwo implements Runnable{

    private int i;
    private int j;

    public AddTwo(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public void run() {
        Main.B.get(i).set(j, Main.B.get(i - 1).get(2 * j) + Main.B.get(i - 1).get(2 * j + 1));
        Main.latch.countDown();
    }

}