package NowCoder.Nowcoder;

/*
    替换空格：
        请实现一个函数，将一个字符串中的每个空格替换成“%20”。
        例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class code02_ReplaceSpace {
    // 方法一：直接用replace函数
    public String replaceSpace1(StringBuffer str) {
        // 每个空格替换成“%20”
        // replace函数
        return str.toString().replace(" ", "%20");
    }

    // 方法二：通过新的字符串来实现替换
    public String replaceSpace2(StringBuffer str) {
        // 每个空格替换成“%20”
        // 通过一个新的字符串来替换
        // 遇到空格，往新字符串中append %20，否则直接append

        StringBuilder newStr = new StringBuilder();
        // 遍历字符串每一个字符
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                newStr.append(c);
            } else {
                newStr.append("%20");
            }
        }
        return newStr.toString();
    }

    /**
     * 法三：从后往前
     * TODO 从后往前可以保证尽量减少空间，因为遇到空格就要把后续的字符往后移
     *      而从前往后的话，每遇到一个空格，就要将后续的字符往后移动一次，会有重复操作
     *
     * 时间复杂度：O(N)
     */
     public String replaceSpace3(StringBuffer str) {
        int spacenum = 0; // 空格数量
        // 首先计算空格的数量
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                spacenum++;
            }
        }

        int oldLength = str.length(); // 原字符串长度
        int newLength = oldLength + spacenum * 2; // 因为空格替换为%20，所以有一个空格，就要将空格后的字符往后移动2格
        // 这里*2是因为，要把原来的空格替换为%20，原来空格占一格，%20占三格，相当于3-1=2，新增2个长度
        str.setLength(newLength);
        int newIndex = newLength - 1;
        // 从后往前遍历空间增加后的字符串
        // 通过双指针遍历，for循环指针用于遍历原字符串，if语句指针用于指向应该移动到的位置
        for (int oldIndex = oldLength - 1; oldIndex >= 0; oldIndex--) { // 遍历原字符串
            if (str.charAt(oldIndex) == ' ') {
                // 如果字符是空格，则将该索引处以及前两个填充为02%
                // TODO 这里替换的字符要反过来填进去
                str.setCharAt(newIndex--, '0');
                str.setCharAt(newIndex--, '2');
                str.setCharAt(newIndex--, '%');
            } else {
                // 如果字符不是空格，则将原字符串该索引处的字符放到空间增加后的最后
                str.setCharAt(newIndex--, str.charAt(oldIndex));
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        String s = "I am boy";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s);
        code02_ReplaceSpace code02_ReplaceSpace = new code02_ReplaceSpace();
        code02_ReplaceSpace.replaceSpace3(stringBuffer);
        System.out.println(stringBuffer.toString());
    }
}

