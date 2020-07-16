package NowCoder;

import java.util.HashMap;
import java.util.Objects;

public class test{

    public static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static void main(String[] args) {
        HashMap<Person, String> map = new HashMap<>();

        map.put(new Person("111"), "AAA");
        map.put(new Person("222"), "BBB");
        map.put(new Person("333"), "CCC");

        System.out.println(map.get(new Person("111")));
        System.out.println(map.get(new Person("222")));
        System.out.println(map.get(new Person("333")));

    }

}