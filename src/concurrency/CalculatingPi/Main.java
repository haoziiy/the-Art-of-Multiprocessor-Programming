package concurrency.CalculatingPi;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sherry on 2017/5/28.
 */

class util{
    public static int i = 0;
    public static int n = 0;
    static Lock lock = new ReentrantLock();
}

public class Main {

    static double PI() throws InterruptedException{ // can be a static method

        double PI;
        int sum = 0;
        int threadNum = 4;
        PIThread[] ts = new PIThread[threadNum];
        for(int i=0; i < threadNum; i++){ // do parallel computations
            ts[i] = new PIThread();
            ts[i].start();
        }
        for(int i=0; i < threadNum; i++) // combine results
        {
            ts[i].join();
            sum += ts[i].sum;
        }
        //System.out.println("sum:"+sum);
        PI = 4.0 * sum / util.n;
        return PI;
    }


    public static void main(String[] args) throws InterruptedException{
        int n;
        double PI;
        System.out.println("蒙特卡洛概率算法计算圆周率:");
        Scanner input = new Scanner(System.in);
        System.out.println("输入点的数量：");
        n = input.nextInt();
        util.n = n;
        long time1 = new Date().getTime();
        PI = PI();
        System.out.println("PI=" + PI);

        long time2 = new Date().getTime();
        System.out.println("用时：" + (time2 - time1) + "毫秒");
    }

}

class PIThread extends Thread{

    double x,y;
    int i, sum;

    public void run() {
        do{
            util.lock.lock();
            try{
                i = util.i;
                util.i = i + 1;
            }
            finally{
                util.lock.unlock();
            }
            if (i <= util.n)
            {
                x = Math.random();
                y = Math.random();
                if ((x*x + y*y) <= 1) {
                    sum++;
                }
            }
        }while (util.i < util.n);
    }
}
