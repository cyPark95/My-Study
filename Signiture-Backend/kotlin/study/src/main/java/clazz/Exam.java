package clazz;

public class Exam {

    interface Bark {
        void bark();
    }

    abstract class Animal implements Bark {

        private final String name;

        public Animal(String name) {
            this.name = name;
        }

        public void eat() {
            System.out.println(name + " 식사 시작 합니다.");
        }

        public String getName() {
            return name;
        }
    }

    class Dog extends Animal {

        private int age;

        public Dog() {
            super("Dog");
        }

        public Dog(String name) {
            super(name);
        }

        @Override
        public void bark() {
            System.out.println(this.getName() + ": 멍멍!!");
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public Exam() {
        var dog = new Dog("바둑이");
        dog.eat();
        dog.bark();

        dog.setAge(10);
        System.out.println("age: " + dog.getAge());

        var user = new User();
        user.setName("홍길동");
        user.setEmail("email@gmail.com");
        user.setAge(20);
        System.out.println(user);

        // 생성자 매개변수의 순서를 준수 해야 한다.
        new User("홍길동", "email@gmail.com", 20);
    }

    public static void main(String[] args) {
        new Exam();
    }
}
