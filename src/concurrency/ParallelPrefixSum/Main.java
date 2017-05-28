package concurrency.ParallelPrefixSum;

import java.util.Date;
import java.util.Random;

/**
 * Created by sherry on 2017/5/28.
 */
public class Main {

    public static class TreeNode{
        int val;
        TreeNode parent;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    public static void main(String[] args){

        int[] arr = new int[100];
        int[] prefix = new int[100];
        Random random = new Random();
        int total = 0;
        // 初始化100个数字元素
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        long time1 = new Date().getTime();


        // System.out.println("数组中数的和为：" + new SumThread(arr,0,arr.length).sum(arr));
        long time2 = new Date().getTime();
        System.out.println("用时：" + (time2 - time1) + "毫秒");
    }



    public static void buildPrefixTree(int[] arr, TreeNode treeNode){
        TreeNode left = new TreeNode(arr[0]);
        left.parent = new TreeNode(left.val);
        left.parent.left = left;

    }
}
