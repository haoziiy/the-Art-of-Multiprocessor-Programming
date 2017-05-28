package concurrency.JavaConcurrency;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;


public class ForkJoinPoolTest2 {

    public static void main(String[] args) throws Exception {
        int arr[] = new int[1000];
        Random random = new Random();
        int total = 0;
        // 初始化100个数字元素
        for (int i = 0; i < arr.length; i++) {
            int temp = random.nextInt(100);

            total += (arr[i] = temp);
        }
        long time1 = new Date().getTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> future = forkJoinPool.submit(new MaxTask(arr, 0,
                arr.length));
        System.out.println("数组里最大的数是：" + future.get());
        long time2 = new Date().getTime();
        System.out.print("用时：" + (time2 - time1) + "毫秒");
        // 关闭线程池
        forkJoinPool.shutdown();
    }
}

class MaxTask extends RecursiveTask<Integer> {

    private static final int MAX = 50;
    private int arr[];
    private int start;
    private int end;

    MaxTask(int arr[], int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    protected Integer compute() {
        int max = 0;

        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                if(arr[i] > max)
                {
                    max = arr[i];
                }
            }
            return max;
        } else {
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            MaxTask left = new MaxTask(arr, start, middle);
            MaxTask right = new MaxTask(arr, middle, end);
            // 并行执行两个小任务
            left.fork();
            right.fork();
            // 把两个小任务累加的结果合并起来
            return left.join() + right.join();
        }
    }

}
