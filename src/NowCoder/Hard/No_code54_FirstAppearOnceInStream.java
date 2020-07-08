package NowCoder.Hard;

/**
 * 字符流中第一个只出现一次的字符：
 *  请实现一个函数用来找出字符流中第一个只出现一次的字符。
 *  例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 *  当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"
 *  如果当前字符流没有存在出现一次的字符，返回#字符。
 *
 * 思路：
 *  创建256长度的数组，存储ASCII码 0 - 255范围的所有字符
 *  索引表示其ASCII码，元素表示是否出现和是否第一次出现
 *
 *  所有元素初始化为 -1，表示没有出现过
 *  当第一次出现时，其对应索引位置上的元素为-1，更新为其在字符流中的位置
 *  当再次出现时，该位置上的元素一定 >= 0，说明出现过一次，那么更新为-2
 *  ==>
 *      -1：没有出现过
 *      >=0：只出现过一次
 *      -2：出现过一次以上
 *  那么，在获取第一次只出现一次的字符时，只需要顺序遍历该数组，找到第一次元素 >=0 的位置，
 *  其对应的索引即为该字符
 *  TODO
 *   (1) 因为ASCII码值在 0-255 范围内，所以数组必须初始化为-1，不然ASCII码为0的字符会找不到
 *   (2) 在寻找时不能以找到第一个为结束条件，必须看该位置元素在字符流中的位置来判断是否是第一次出现的
 *      因此数组元素应该存 在字符流中的位置
 */
public class No_code54_FirstAppearOnceInStream {
    private int[] arr = new int[256];

    // TODO 必须使用index表示字符流输入的字符个数
    private int index;

    public No_code54_FirstAppearOnceInStream() {
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = -1;
        }

        this.index = 0;
    }

    public void insert(char ch){
        if (arr[ch] == -1){ // 没有出现过
            arr[ch] = index; // TODO 一定要存在字符流中的位置
        }else if (arr[ch] >= 0){ // 出现过一次
            arr[ch] = -2;
        }

        // TODO 不管出没出现，都要更新字符流的位置
        index++;
    }

    public char getChar(){
        int res = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            // TODO 不光是要找数组中 >=0 的数的位置，还要看该位置的元素是否是字符流位置最小的
            if (arr[i] >= 0 && arr[i] < min){
                res = i;
                min = arr[i];
            }
        }

        if (res == -1){
            return '#';
        }else {
            return (char) res;
        }
    }

    public static void main(String[] args) {
        String s = "google";

        No_code54_FirstAppearOnceInStream firstAppearOnceInStream = new No_code54_FirstAppearOnceInStream();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            firstAppearOnceInStream.insert(ch);

            System.out.print(firstAppearOnceInStream.getChar() + " "); // g g g # l l
        }
    }
}
