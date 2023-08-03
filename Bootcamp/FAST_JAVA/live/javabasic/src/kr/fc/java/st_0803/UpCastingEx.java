package kr.fc.java.st_0803;

import kr.fc.poly.Animal;
import kr.fc.poly.Dog;

public class UpCastingEx {

    public static void main(String[] args) {
        // Dog 객체를 생성하는 3가지 방법
        Dog dog = new Dog();            // 직접 접근
        Animal animalDog = new Dog();   // 간접 접근(UpCasting)
        Object objectDog = new Dog();   // 자바에서 제공하는 root 클래스
    }
}
