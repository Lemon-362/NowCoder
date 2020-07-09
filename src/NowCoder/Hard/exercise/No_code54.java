package NowCoder.Hard.exercise;



public class No_code54 {
    public int[] arr = new int[256];

    public int index;

    public No_code54() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }
        this.index = 0;
    }

    public void insert(char ch){
        if (arr[ch] == -1){
            arr[ch] = index;
        }else if (arr[ch] >= 0){
            arr[ch] = -2;
        }
        index++;
    }

    public char getChar(){
        int minIndex = Integer.MAX_VALUE;
        int res = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0 && minIndex > arr[i]){
                minIndex = arr[i];
                res = i;
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

        No_code54 firstAppearOnceInStream = new No_code54();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            firstAppearOnceInStream.insert(ch);

            System.out.print(firstAppearOnceInStream.getChar() + " "); // g g g # l l
        }
    }
}
