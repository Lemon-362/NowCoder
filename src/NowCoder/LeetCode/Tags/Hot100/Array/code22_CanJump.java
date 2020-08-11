package NowCoder.LeetCode.Tags.Hot100.Array;

/*
55. 跳跃游戏
    给定一个非负整数数组，你最初位于数组的第一个位置。
    数组中的每个元素代表你在该位置可以跳跃的最大长度。
    判断你是否能够到达最后一个位置。

 */
public class code22_CanJump {

    /*
    贪心:
        mostJump表示最远可以跳到的位置
        只要遍历数组时, mostJump能跳到数组最后一个位置及其之后, 那么就返回true

        mostJump = index + arr[index]
        由于可能当前位置 > mostJump之前可以最远跳到的位置,
        所以mostJump = Math.max{mostJump, index + arr[index]}
        并且, 如果当前位置是之前跳不到的, 那么不更新mostJump

     */
    public static boolean canJump(int[] arr) {
        if (arr == null || arr.length < 1) {
            return false;
        }

        // 之前最远可以跳到的位置
        int mostJump = 0;
        int len = arr.length;

        for (int i = 0; i < arr.length; i++) {
            // 只在之前最远可以跳到当前i位置及其之后时, 才更新
            if (i <= mostJump){
                mostJump = Math.max(mostJump, i + arr[i]);

                // 判断: 如果mostJump已经可以跳到数组最后或者再往后, 那么返回true
                if (mostJump >= len - 1){
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 1, 4};
        int[] arr2 = {3, 2, 1, 0, 4};

        System.out.println(canJump(arr1)); // true
        System.out.println(canJump(arr2)); // false
    }
}
