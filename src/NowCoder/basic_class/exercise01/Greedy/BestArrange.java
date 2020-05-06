package NowCoder.basic_class.exercise01.Greedy;

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

    public static int method(Program[] programs, int start) {
        if (programs == null) {
            return 0;
        }
        int cur = start;
        int res = 0;
        Arrays.sort(programs, new myComparator());
        for (int i = 0; i < programs.length; i++) {
            if (cur <= programs[i].start){
                res++;
                cur = programs[i].end;
            }
        }
        return res;
    }

    public static class myComparator implements Comparator<Program> {
        public int compare(Program o1, Program o2){
            return o1.end - o2.end;
        }
    }

    public static void main(String[] args) {

    }
}
