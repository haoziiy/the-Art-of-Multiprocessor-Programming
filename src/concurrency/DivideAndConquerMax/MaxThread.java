package concurrency.DivideAndConquerMax;

/**
 * Created by sherry on 2017/5/14.
 */
public class MaxThread extends Thread{
    int low;
    int high;
    int[] arr;
    int max = Integer.MIN_VALUE;
    MaxThread(int[] arr, int low, int high){
        this.arr = arr;
        this.low = low;
        this.high = high;
    }
    public void run(){
        if (high > low){
            for (int i = low;i < high;i++)
            {
                if (max < arr[i])
                    max = arr[i];
            }
        }
        else {
            MaxThread left = new MaxThread(arr, low, (low + high)/2);
            MaxThread right = new MaxThread(arr, (low + high)/2, high);
            left.start();
            right.start();
            try {
                left.join();
                right.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            max = Math.max(left.max, right.max);
        }
    }
    public int max(int[] arr)
    {
        MaxThread t = new MaxThread(arr, 0, arr.length);
        t.run();
        return t.max;
    }
}
