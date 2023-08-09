package kr.fc.poly;

public interface Pet {  // new 불가능

    static void description() { // Pet.description
        System.out.println("Static Method");
    }

    default void play() {
        System.out.println("집에서 함께 생활한다.");
    }
}
