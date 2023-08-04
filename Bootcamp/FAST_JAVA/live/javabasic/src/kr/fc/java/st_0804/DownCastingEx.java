package kr.fc.java.st_0804;

import kr.fc.poly.Animal;
import kr.fc.poly.Cat;
import kr.fc.poly.Dog;

public class DownCastingEx {

    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.eat();
        animal = new Cat();
        animal.eat();
        // 다형성: 상위 클래스가 동일한 메시지로 하위 클래스를 서로 다르게 동작시키는 객체지향 원리

        ((Cat) animal).night(); // 연산자 우선순위에 의해 Casting 보다 .연산자가 우선위를 갖는다.
        Cat cat = (Cat) animal; // DownCasting
        cat.night();
    }
}
