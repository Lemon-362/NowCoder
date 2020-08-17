package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
15. 三数之和
    给你一个包含 n 个整数的数组 nums，
    判断 nums 中是否存在三个元素 a，b，c ，
    使得 a + b + c = 0 ？
    请你找出所有满足条件且不重复的三元组

 */
public class code27_ThreeSum {

    /*
    方法一: 剩余数, 使用HashMap存储
         以每个位置作为第一个数，在后续数组中找两数之和等于该位置的负数
         TODO 使用HashMap遍历，对于数组有重复元素时，会出现重复解
              对于两数之和不能去重
     */
    public static List<List<Integer>> threeSum(int[] arr){
        if (arr == null || arr.length < 3){
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(arr);

        // 以每个位置作为第一个数
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            if (num > 0){
                break;
            }

            // 去重
            if (i > 0 && arr[i] == arr[i - 1]){
                continue;
            }

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < arr.length; j++) {
                if (map.containsKey(-num - arr[j])){
                    List<Integer> list = new ArrayList<>();
                    list.add(num);
                    list.add(arr[j]);
                    list.add(-num - arr[j]);
                    res.add(list);
                }

                map.put(arr[j], j);
            }
        }

        return res;
    }

    /*
        2. 剩余数：双指针法 —— 去除重复解
        TODO 为了去除重复解, 可以先对数组进行排序
         双指针可以保证两数之和去重，因为排过序，所以如果相同元素，前后指针直接跳过

         a + b = -c ==> 转换成两数之和
     */
    public static List<List<Integer>> threeSum2(int[] arr){
        if (arr == null || arr.length < 3){
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(arr);

        // 遍历数组, 以每个元素为起点, 在后面找 a+b = -c的两个数
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            // 因为排过序了, 所以如果num>0, 那么num后面的也一定>0, 就不可能再找到两数之和为负数的
            if (num > 0){
                break;
            }

            // TODO 因为排过序了, 所以对于相同元素, 可以直接跳过以下一个元素为开头, 否则会有重复解
            if (i > 0 && arr[i] == arr[i - 1]){
                continue;
            }

            // 以num开头, 往后找两数之和=-num
            // 因为排过序了, 所以可以使用二分
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right){
                int sum = arr[left] + arr[right];

                if (sum == -num){
                    List<Integer> list = new ArrayList<>();
                    list.add(num);
                    list.add(arr[left]);
                    list.add(arr[right]);
                    res.add(list);

                    // TODO 继续向内寻找, 对于重复元素, 也需要跳过, 以保证去重
                    while (left < right && arr[left] == arr[left + 1]){
                        left++;
                    }
                    while (right > left && arr[right] == arr[right - 1]){
                        right--;
                    }

                    // 去重完成后, 再往内收缩, 继续找下一组
                    left++;
                    right--;
                }else if (sum > -num){ // 说明right的值太大
                    // 由于只在一边收缩, 所以不需要考虑去重的问题
                    right--;
                }else { // 说明left的值太大
                    // 由于只在一边收缩, 所以不需要考虑去重的问题
                    left++;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> lists = threeSum2(arr);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
//        -1 -1 2
//        -1 0 1
    }
}
