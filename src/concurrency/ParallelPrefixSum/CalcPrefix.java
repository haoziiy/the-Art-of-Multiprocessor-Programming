package concurrency.ParallelPrefixSum;

/**
 * Created by sherry on 2017/5/27.
 */

public class CalcPrefix implements Runnable{

    private int i;
    private int j;
    public CalcPrefix(int i, int j) {
        this.i = i;
        this.j = j;
    }
    @Override
    public void run() {
        if (j % 2 == 1) {
            Main.C.get(i).set(j, Main.C.get(i - 1).get((j - 1) / 2));
        } else if (j == 0) {
            Main.C.get(i).set(j, Main.B.get(Main.m - i).get(0));
        } else {
            Main.C.get(i).set(j, Main.C.get(i - 1).get(j / 2 - 1) + Main.B.get(Main.m - i).get(j));
        }
        Main.latch.countDown();
    }

}
