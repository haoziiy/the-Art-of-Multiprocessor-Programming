package concurrency.ParallelPrefixSum;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static final int m = 4;
    public static ArrayList<Integer> values = prepareNumbers();
    public static ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<ArrayList<Integer>> C = new ArrayList<ArrayList<Integer>>();
    public static CountDownLatch latch;

    public static void main(String[] args) throws InterruptedException {
        printNumbers();

        B.add(new ArrayList<Integer>(1 << m));
        for (int i = 0; i < values.size(); i++) B.get(0).add(values.get(i));

        for (int i = 1; i <= m; i++) {
            final int l = 1 << (m - i);
            B.add(new ArrayList<Integer>(l));
            for (int j = 0; j < l; j++) B.get(i).add(0);

            latch = new CountDownLatch(l);
            for (int j = 0; j < l; j++) {
                new Thread(new AddTwo(i, j)).start();
            }
            latch.await();
        }

        printResult(B, "B:");

        for (int i = 0; i <= m; i++) {
            final int l = 1 << i;
            C.add(new ArrayList<Integer>(l));
            for (int j = 0; j < l; j++) C.get(i).add(0);

            latch = new CountDownLatch(l);
            for (int j = 0; j < l; j++) {
                new Thread(new CalcPrefix(i, j)).start();
            }
            latch.await();
        }
        printResult(C, "C:");
    }

    private static void printResult(ArrayList<ArrayList<Integer>> l, String name) {
        System.out.println(name);
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.get(i).size(); j++)
                System.out.print(l.get(i).get(j) + ", ");
            System.out.println();
        }
    }

    private static ArrayList<Integer> prepareNumbers() {
        ArrayList<Integer> values = new ArrayList<Integer>();

        Random rand = new Random();
        for (int i = 0; i < 1 << m; i++) {
            values.add(rand.nextInt(100));
        }
        return values;
    }

    private static void printNumbers() {
        System.out.print("Numbers: ");
        for (int i = 0; i < values.size(); i++) System.out.print(values.get(i) + ", ");
        System.out.println();
    }


}
