package kr.fc.poly;

public interface Animal {

    default void eat() {
        System.out.println("?");
    }
}
