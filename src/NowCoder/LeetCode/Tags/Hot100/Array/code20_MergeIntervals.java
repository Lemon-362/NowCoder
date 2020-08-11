package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.*;

/*
56. 合并区间
    给出一个区间的集合，请合并所有重叠的区间

 */
public class code20_MergeIntervals {
    public static class Interval {
        private int start;
        private int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /*
    类似于basic_code38_BestArrange
        basic是要按照结束时间排序, 然后看之前的结束时间是否在当前的开始时间后

        本题应该按照start排序
        然后看之前的end是否在当前的开始时间后
        (1) 如果不在, 说明不在当前区间内, 是一个独立的区间
        (2) 如果在, 说明可以合并, 此时需要看当前区间的end和之前的end哪个大, 放哪个

        由于start和end永远是一对, 所以用ArrayList存储时, 只要保证每次是存这一对即可
     */
    public static int[][] merge(int[][] arr) {

        Interval[] intervals = new Interval[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intervals[i] = new Interval(arr[i][0], arr[i][1]);
        }

        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        ArrayList<Integer> list = new ArrayList<>();
        list.add(intervals[0].start);
        list.add(intervals[0].end);

        for (int i = 1; i < intervals.length; i++) {
            // 说明之前的end不包含在现在的区间内
            if (intervals[i].start > list.get(list.size() - 1)){
                list.add(intervals[i].start);
                list.add(intervals[i].end);
            }else { // 包含
                int num = list.remove(list.size() - 1);
                list.add(Math.max(num, intervals[i].end));
            }
        }

        int[][] res = new int[list.size() / 2][2];
        for (int i = 0; i < res.length; i++) {
            res[i][0] = list.get(2 * i);
            res[i][1] = list.get(2 * i + 1);
        }

        return res;
    }

    public static void main(String[] args) {

        int[][] arr = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] arr = {{1, 4}, {2, 3}};

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
