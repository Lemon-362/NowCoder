package NowCoder;

import java.util.HashMap;
import java.util.Scanner;

public class test3 {

    public static String process(HashMap<String, Integer> timeMap,
                                 HashMap<String, Integer> numMap) {

        int num = 0;

        String res = "";

        for (String key : timeMap.keySet()) {
            if (timeMap.get(key) > 180) {
                if (num < numMap.get(key)) {
                    num = numMap.get(key);
                    res = key;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        HashMap<String, Integer> timeMap = new HashMap<>();
        HashMap<String, Integer> numMap = new HashMap<>();

        while (scanner.hasNext()) {

            String s = scanner.nextLine();

            String[] str = s.split(" ");

            if (!timeMap.containsKey(str[1])) {
                timeMap.put(str[1], Integer.parseInt(str[2]));
            } else {
                timeMap.put(str[1], timeMap.get(str[1]) + Integer.parseInt(str[2]));
            }

            if (!numMap.containsKey(str[1])) {
                numMap.put(str[1], 1);
            } else {
                numMap.put(str[1], numMap.get(str[1]) + 1);
            }

            System.out.println(process(timeMap, numMap));
        }


    }
}
