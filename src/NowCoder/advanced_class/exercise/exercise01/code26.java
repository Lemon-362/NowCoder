package NowCoder.advanced_class.exercise.exercise01;

import java.util.LinkedList;

public class code26 {
    public static class ReturnData {
        private int result;
        private int index;

        public ReturnData(int result, int index) {
            this.result = result;
            this.index = index;
        }
    }

    public static ReturnData process(char[] str, int i) {
        LinkedList<String> list = new LinkedList<>();
        int preNum = 0;

        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                preNum = preNum * 10 + str[i++] - '0';
            } else if (str[i] != '(') {
                addNum(list, preNum);
                list.addLast(String.valueOf(str[i++]));
                preNum = 0;
            } else {
                ReturnData returnData = process(str, i + 1);
                preNum = returnData.result;
                i = returnData.index + 1;
            }
        }

        addNum(list, preNum);
        int result = getNum(list);

        return new ReturnData(result, i);
    }

    public static int getNum(LinkedList<String> list) {
        int res = 0;
        boolean add = true;
        String cur = null;

        while (!list.isEmpty()) {
            cur = list.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                int num = Integer.parseInt(cur);
                res += add ? num : -num;
            }
        }

        return res;
    }

    public static void addNum(LinkedList<String> list, int curNum) {
        if (!list.isEmpty()) {
            String operator = list.pollLast();
            if (operator.equals("+") || operator.equals("-")) {
                list.addLast(operator);
            } else {
                int num = Integer.parseInt(list.pollLast());
                curNum = operator.equals("*") ? num * curNum : num / curNum;
            }
        }

        list.addLast(String.valueOf(curNum));
    }

    public static int getValue(String str) {
        return process(str.toCharArray(), 0).result;
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp)); // -1816

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp)); // 745

        exp = "10-5*3";
        System.out.println(getValue(exp)); // -5

        exp = "-3*4";
        System.out.println(getValue(exp)); // -12

        exp = "3+1*4";
        System.out.println(getValue(exp)); // 7
    }
}
