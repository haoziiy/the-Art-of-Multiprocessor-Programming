package concurrency.SumThread;

/**
 * Created by sherry on 2017/5/23.
 */
public class SumThread extends Thread {

    int lo;
    int hi;
    int[] arr; // arguments
    int ans = 0; // result

    SumThread(int[] a, int l, int h) {
        this.arr = a;
        this.lo = l;
        this.hi = h;
    }

    public void run() { // override
        if (hi > lo)
        for (int i = lo; i < hi; i++)
            ans += arr[i];
    else{
            SumThread left = new SumThread(arr, lo, (hi + lo) / 2);
            SumThread right = new SumThread(arr, (hi + lo) / 2, hi);
            left.start();
            right.start();
            try {
                left.join(); // don’t move this up a line – why?
                right.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ans = left.ans + right.ans;
        }
    }
    int sum(int[] arr){
        SumThread t = new SumThread(arr,0,arr.length);
        t.run();
        return t.ans;
    }
}

