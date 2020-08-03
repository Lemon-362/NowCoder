package NowCoder.Nowcoder.Tags.String;

public class code54 {
    public int[] arr = new int[256];

    public int index;

    public code54() {
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

        this.index++;
    }

    public char getChar(){

        char res = ' ';
        int indexx = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0 && indexx > arr[i]){
                indexx = arr[i];
                res = (char) i;
            }
        }

        return indexx == Integer.MAX_VALUE ? '#' : res;
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
