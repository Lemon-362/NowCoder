package NowCoder.basic_class.Tags.Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {
    public static class Program {
        private int start;
        private int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int BestArrange(Program[] programs, int start){
        if (programs == null || programs.length < 1 || start < 0){
            return 0;
        }

        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        });

        int res = 0;
        int cur = start;
        for (int i = 0; i < programs.length; i++) {
            if (cur <= programs[i].start){
                cur = programs[i].end;
                res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] start = {1, 2, 4, 6, 8};
        int[] end = {3, 5, 7, 9, 10};

        Program[] programs = new Program[start.length];
        for (int i = 0; i < programs.length; i++) {
            programs[i] = new Program(start[i], end[i]);
        }

        System.out.println(BestArrange(programs, 0)); // 3
    }
}
