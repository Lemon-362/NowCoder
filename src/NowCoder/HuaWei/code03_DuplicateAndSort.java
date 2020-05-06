package NowCoder.HuaWei;

import java.util.Arrays;
import java.util.Scanner;

/*
    对数组去重和排序：
        对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。
        然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。
        请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。
 */
public class code03_DuplicateAndSort {
    public static void main(String[] args){
        // 只保留一个数字，删掉重复数字，然后从小到大排序
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            // 输入两行，第一行输入个数，第二行输入数组元素
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            // 输出排序和去重后的数组
            for(int i = 0; i < arr.length; i++){
                if(i == 0){
                    System.out.println(arr[i]);
                }else if(arr[i] != arr[i - 1]){
                    System.out.println(arr[i]);
                }
            }
        }
    }

}
