package kr.fc.java.st_0809;

import kr.fc.poly.Bird;
import kr.fc.poly.Sparrow;

public class InheritanceChainingEx {

    public static void main(String[] args) {
        Bird bird = new Sparrow("참새");
        System.out.println(bird.getName());
    }
}
