package kr.fc.java.st_0803;

import kr.fc.poly.Animal;
import kr.fc.poly.Cat;
import kr.fc.poly.Dog;

public class PolymorphismEx {

    public static void main(String[] args) {
        // 정보 은닉, 상속, 다형성(추상 클래스, 인터페이스)
        // 개(Dog), 고양이(Cat) 객체
        Dog dog = new Dog();
        Cat cat = new Cat();

        printEat(dog);
        printEat(cat);
    }

    // Dog, Cat,... 객체를 매개변수로 받아서 eat()를 동작시키는 메서드를 정의
    private static void printEat(Animal animal) {   // 다형성 인수
        animal.eat();   // 컴파일 시점에서는 부모(Animal)의 eat()
                        // 실행시점에는 자식의 eat()가 실행된다. -> 동적바인딩(재정의)
    }
}
