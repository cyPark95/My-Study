package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exam {

    public Exam() {
        list();
        map();
    }

    private void list() {
        var mutableUsers = new ArrayList<>();
        mutableUsers.add(new User("1", 10));
        mutableUsers.add(new User("2", 20));
        mutableUsers.add(new User("3", 30));

        var immutableUsers = List.of(
                new User("4", 40),
                new User("5", 50)
        );
        // add 메서드 지원 -> Exception 발생
//        immutableUsers.add(new User("5", 50));

        for (var user : immutableUsers) {
            System.out.println(user);
        }

        immutableUsers.forEach(System.out::println);

        for (int index = 0; index < immutableUsers.size(); index++) {
            System.out.println("index: " + index + " / users: " + immutableUsers.get(index));
        }
    }

    private void map() {
        var immutableMap = Map.of(
                "name", "name",
                "age", 20
        );

        var mutableMap = new HashMap<String, Object>();
        mutableMap.put("key", "value");

        mutableMap.put("key", "mutable");
        var value = mutableMap.get("key");
        System.out.println(value);
    }

    public static void main(String[] args) {
        new Exam();
    }

    class User {

        private String name;
        private int age;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
