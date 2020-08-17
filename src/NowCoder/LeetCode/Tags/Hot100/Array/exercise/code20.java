package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
56. 合并区间
    给出一个区间的集合，请合并所有重叠的区间

 */
public class code20 {

    public static class Data{
        private int start;
        private int end;

        public Data(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int[][] merge(int[][] arr) {

        Data[] data = new Data[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = new Data(arr[i][0], arr[i][1]);
        }

        Arrays.sort(data, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.start - o2.start;
            }
        });

        List<Integer> list = new ArrayList<>();
        list.add(data[0].start);
        list.add(data[0].end);

        for (int i = 1; i < data.length; i++) {
            // 看上一个end是否在当前区间内
            int lastEnd = list.get(list.size() - 1);
            if (lastEnd < data[i].start){ // 不在当前区间内
                list.add(data[i].start);
                list.add(data[i].end);
            }else { // 在区间内或者超过区间
                list.remove(list.size() - 1);
                // 要看当前end和lastEnd哪个更长
                int curEnd = data[i].end;
                int maxEnd = Math.max(lastEnd, curEnd);
                list.add(maxEnd);
            }
        }

        // 由于list存放的都是start-end一对, 所以最终合并后的区间有size/2组
        int len = list.size() / 2;
        int[][] res = new int[len][2];
        for (int i = 0; i < res.length; i++) {
            res[i][0] = list.get(2 * i);
            res[i][1] = list.get(2 * i + 1);
        }

        return res;
    }

    public static void main(String[] args) {

//        int[][] arr = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] arr = {{1, 4}, {2, 3}};

        int[][] res = merge(arr);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
//        1 6
//        8 10
//        15 18

//        1 4
    }
}
