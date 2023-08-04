package kr.fc.java.st_0804;

import kr.fc.poly.Bird;
import kr.fc.poly.Sparrow;

public class AbstractEx {

    public static void main(String[] args) {
        Bird bird = new Sparrow();
        bird.fly(); // 다형성이 보장된다.
        bird.eat();
        ((Sparrow)bird).sound();
    }
}
