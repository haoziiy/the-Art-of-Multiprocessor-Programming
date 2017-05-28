package concurrency.CalculatingPi;

import java.util.Date;
import java.util.Scanner;

/**
 * Created by sherry on 2017/5/28.
 */
public class MainSequential {

    public static void main(String[] args) throws InterruptedException {
        int n;
        double PI;
        System.out.println("(顺序执行)蒙特卡洛概率算法计算圆周率:");
        Scanner input = new Scanner(System.in);
        System.out.println("输入点的数量：");
        n = input.nextInt();
        util.n = n;
        long time1 = new Date().getTime();
        PI = MontePI(n);
        System.out.println("PI=" + PI);

        long time2 = new Date().getTime();
        System.out.println("用时：" + (time2 - time1) + "毫秒");
    }

    static double MontePI(int n) {
        double PI;
        double x, y;
        int i, sum;
        sum = 0;
        for (i = 1; i < n; i++) {
            x = Math.random();
            y = Math.random();
            if ((x * x + y * y) <= 1) {
                sum++;
            }
        }
        PI = 4.0 * sum / n;
        return PI;
    }
}
