package NowCoder.Hard.HardSummary;

import java.util.LinkedList;

/*
    表达式计算：
        给定一个字符串str，包含整数、运算符号和括号
        返回计算结果
 */
public class code26_ExpressionCompute {
    public static class ReturnData { // 子过程返回的东西: 一个是计算结果, 还有一个是 )的位置
        private int result;
        private int index;

        public ReturnData(int result, int index) {
            this.result = result;
            this.index = index;
        }
    }

    // 递归函数f(i): str从i位置开始计算, 直到遇到 ( 或者终止位置停止
    public static ReturnData process(char[] str, int i){
        int preNum = 0;
        // 每次只在遇到符号时, 将之前收集的数和该符号一起放进去
        LinkedList<String> list = new LinkedList<>();

        while (i < str.length && str[i] != ')'){
            if (str[i] >= '0' && str[i] <= '9'){
                preNum = preNum * 10 + str[i++] - '0';
            }else if (str[i] != '('){ // 遇到符号, 将之前收集的数和该符号一起放进去
                // 放入之前的数, 有可能需要先计算*或/
                addNum(list, preNum);
                // 放入当前的符号
                list.addLast(String.valueOf(str[i++]));
                preNum = 0;
            }else {
                // 遇到 (, 进入递归, 子过程返回 (到) 内的计算结果, 以及 )的位置给我, 但是我需要的是 )的下一个位置
                ReturnData  returnData = process(str, i + 1);
                preNum = returnData.result;
                i = returnData.index + 1;
            }
        }
        // 放入之前的数
        addNum(list, preNum);
        // 此时栈中没有*或/, 只有+/-, 计算从i位置开始到 )结束的整个表达式的值, 并向上返回
        int result = getNum(list);
        // 向上返回的是 ()内的计算结果, 以及从什么位置开始计算的
        return new ReturnData(result, i);
    }

    // 只有+,-的计算, 因为是 一个数+符号 一起放入的, 所以可以从头部开始计算
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

    // 放入之前收集的数, 可能此时的栈顶是*,/, 那么需要先弹出两个元素和当前要放入的数进行计算, 再放入计算结果
    public static void addNum(LinkedList<String> list, int curNum){
        if (!list.isEmpty()){
            // 弹出一个元素, 看栈顶是否为*,/
            String operator = list.pollLast();
            if (operator.equals("+") || operator.equals("-")){
                // 遇到的是+,-, 再放进去
                list.addLast(operator);
            }else { // 遇到的是*,/, 那么就再弹出一个, 此时就是数, 进行计算, 直接更新要放进去的数的值
                int lastNum = Integer.parseInt(list.pollLast());
                curNum = operator.equals("*") ? lastNum * curNum : lastNum / curNum;
            }
        }
        // 将要放进去的数放入栈中(该数可能是已经更新过的了)
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
