package NowCoder;

public class test5 {

    public static void main(String[] args) {

        int a = 7;
        Integer b = new Integer(7);
        System.out.println(a == b); // true
        System.out.println(b.equals(a)); // true

        Integer aa = new Integer(7);
        Integer bb = new Integer(7);
        System.out.println(aa == bb); // false
        System.out.println(aa.equals(bb)); // true
    }
}
