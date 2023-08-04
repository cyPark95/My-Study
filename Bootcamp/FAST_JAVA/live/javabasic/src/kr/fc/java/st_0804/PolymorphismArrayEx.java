package kr.fc.java.st_0804;

import kr.fc.poly.Animal;
import kr.fc.poly.Cat;
import kr.fc.poly.Dog;

public class PolymorphismArrayEx {

    public static void main(String[] args) {
        // 1. 다형성 배열
        // Q. Dog, Cat 을 저장할 [배열을 생성]하세요.
        Animal[] animals = new Animal[2];
        animals[0] = new Dog(); // eat()
        animals[1] = new Cat(); // eat(), night()

        // Q. for 문을 통해 자식 타입의 모든 메서드를 실행하세요.
        for (Animal animal : animals) {
            animal.eat();
            if (animal instanceof Cat cat) {    // type 체크: instanceof
                cat.night();
            }
        }

        // 2. 다형성 인수
        Dog dog = new Dog();
        Cat cat = new Cat();
        display(dog);
        display(cat);
    }

    private static void display(Animal animal) {
        // 재정의를 하지 않으면 -> 다형성을 보장하지 않는다.
        animal.eat();   // 다형성, 동적 바인딩(상속, 재정의, UpCasting)
        if (animal instanceof Cat cat) {
            cat.night();
        }
    }
}
