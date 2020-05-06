package NowCoder;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        // Creating an empty LinkedList
        LinkedList<String> list = new LinkedList<String>();

        // Using add() method to add elements in the list
        list.add("Geeks");
        list.add("for");
        list.add("Geeks");
        list.add("10");
        list.add("20");

        // Displaying the list
        System.out.println("LinkedList:" + list);

        // Fetching first element from the list
        System.out.println("The first element is: " + list.getFirst());

        System.out.println(list);

    }
}