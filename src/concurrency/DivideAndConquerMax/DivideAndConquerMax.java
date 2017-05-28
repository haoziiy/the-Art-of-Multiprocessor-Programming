package concurrency.DivideAndConquerMax;

import java.util.Date;
import java.util.Random;

/**
 * Created by sherry on 2017/5/14.
 */
public class DivideAndConquerMax {
    public static void main(String[] args){
        int arr[] = new int[1000];
        Random random = new Random();
        int total = 0;
        // 初始化100个数字元素
        for (int i = 0; i < arr.length; i++) {
            int temp = random.nextInt(100);

            total += (arr[i] = temp);
        }
        long time1 = new Date().getTime();
        System.out.println("数组中最大的数是：" + new MaxThread(arr,0,arr.length).max(arr));
        long time2 = new Date().getTime();
        System.out.println("用时：" + (time2 - time1) + "毫秒");

    }
}
