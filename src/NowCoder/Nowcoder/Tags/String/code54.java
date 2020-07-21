package NowCoder.Nowcoder.Tags.String;

public class code54 {
    private int[] arr = new int[128];

    private int index;

    public code54(){
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
        char res = ' ';
        int index = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0 && arr[i] < index){
                index = arr[i];
                res = (char) i;
            }
        }

        return index != Integer.MAX_VALUE ? res : '#';
    }

    public static void main(String[] args) {
        String s = "google";

        code54 firstAppearOnceInStream = new code54();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            firstAppearOnceInStream.insert(ch);

            System.out.print(firstAppearOnceInStream.getChar() + " "); // g g g # l l
        }
    }
}
