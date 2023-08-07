package kr.fc.java.st_0804;

public class TodayCheck {

    public static void main(String[] args) {
        // 3.하위 클래스들의 인스턴스를 생성하고 makeSound()와 move() 메서드를 호출하면, 다음과 같은 출력이 나타납니다
        // - 출력결과-
        // 개가 멍멍합니다: 멍! 멍!
        // 동물이 움직입니다.
        // 고양이가 야옹합니다: 야옹! 야옹!
        // 동물이 움직입니다.
        // 소가 음메를 합니다: 음메! 음메!
        // 동물이 움직입니다.
        Animal[] animals = new Animal[3];
        animals[0] = new Dog();
        animals[1] = new Cat();
        animals[2] = new Cow();

        for (Animal animal : animals) {
            animal.move();
            animal.makeSound();
        }
    }

    private interface Animal {
        // 1. Animal 클래스에는 모든 동물들에게 공통적인 move() 메서드를 제공합니다.
        default void move() {
            System.out.println("동물이 움직입니다.");
        }

        // 동물에 맞는 소리를 제공하기 위해서 makeSound() 메서드를 제공합니다.
        void makeSound();
    }
    private static class Dog implements Animal {

        // 2.Dog 하위 클래스는 makeSound() 메서드를 오버라이딩하여 각각의 동물에 맞는 소리를 제공하지만, move() 메서드는 Animal 클래스로부터 상속받습니다.
        @Override
        public void makeSound() {
            System.out.println("개가 멍멍합니다: 멍! 멍!");
        }
    }

    private static class Cat implements Animal {
        // 2.Cat 하위 클래스는 makeSound() 메서드를 오버라이딩하여 각각의 동물에 맞는 소리를 제공하지만, move() 메서드는 Animal 클래스로부터 상속받습니다.
        @Override
        public void makeSound() {
            System.out.println("고양이가 야옹합니다: 야옹! 야옹!");
        }
    }

    private static class Cow implements Animal {
        // 2.Cow 하위 클래스는 makeSound() 메서드를 오버라이딩하여 각각의 동물에 맞는 소리를 제공하지만, move() 메서드는 Animal 클래스로부터 상속받습니다.
        @Override
        public void makeSound() {
            System.out.println("소가 음메를 합니다: 음메! 음메!");
        }
    }
}
