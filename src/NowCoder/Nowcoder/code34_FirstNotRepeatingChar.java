package NowCoder.Nowcoder;

/*
    在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
    并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class code34_FirstNotRepeatingChar {
    /**
     * 将HashMap转换为int数组，节省空间
     */
    public static char FirstNotRepeatingChar(String str) {
        // A - Z ： 65 - 90  a - z ： 97 - 122
        // 借助哈希表的思想，但是不需要使用哈希表，因为可以用数组的索引来表示字符的ASCII码值
        // 让A表示0索引，之后的字符只需要 -65，就可以得到对应的索引值
        // arr数组 索引表示字符，元素表示出现次数
        int[] arr = new int[58]; // 122-65+1=58
        // 遍历字符串
        for(int i = 0; i < str.length(); i++){
            arr[(int)(str.charAt(i)) - 65] += 1; // 出现一次，元素+1
        }
        // 再次遍历字符串，依次查看每个字符出现的次数
        for(int i = 0; i < str.length(); i++){
            if(arr[(int)(str.charAt(i)) - 65] == 1){
                return str.charAt(i); // 返回它的位置
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String str = "abaccdeff";
        System.out.println(FirstNotRepeatingChar(str)); // b
    }
}
