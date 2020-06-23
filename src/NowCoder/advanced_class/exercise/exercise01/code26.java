package NowCoder.advanced_class.exercise.exercise01;

import java.util.LinkedList;
import java.util.List;

public class code26 {
    public static class ReturnData {
        private int result;
        private int index;

        public ReturnData(int result, int index) {
            this.result = result;
            this.index = index;
        }
    }

    public static ReturnData process(char[] str, int i){
        int preNum = 0;
        LinkedList<String> list = new LinkedList<>();

        while (i < str.length && str[i] != ')'){
            if (str[i] >= '0' && str[i] <= '9'){
                preNum = preNum * 10 + str[i++] - '0';
            }else if (str[i] != '('){
                addNum(list, preNum);

                list.addLast(String.valueOf(str[i++]));

                preNum = 0;
            }else {
                ReturnData returnData = process(str, i + 1);
                i = returnData.index + 1;
                preNum = returnData.result;
            }
        }

        addNum(list, preNum);

        int res = getNum(list);

        return new ReturnData(res, i);
    }

    public static int getNum(LinkedList<String> list){
        boolean add = true;
        int res = 0;
        String value;

        while (!list.isEmpty()){
            value = list.pollFirst();
            if (value.equals("+")){
                add = true;
            }else if (value.equals("-")){
                add = false;
            }else {
                int num = Integer.parseInt(value);
                res += add ? num : -num;
            }
        }

        return res;
    }

    public static void addNum(LinkedList<String> list, int curNum){
        if (!list.isEmpty()){
            String operator = list.pollLast();
            if (operator.equals("+") || operator.equals("-")) {
                list.addLast(operator);
            }else {
                int lastNum = Integer.parseInt(list.pollLast());
                curNum = operator.equals("*") ? lastNum * curNum : lastNum / curNum;
            }
        }

        list.addLast(String.valueOf(curNum));
    }

    public static int getValue(String s){
        return process(s.toCharArray(), 0).result;
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
