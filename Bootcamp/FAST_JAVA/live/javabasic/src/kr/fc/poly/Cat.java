package kr.fc.poly;

public class Cat implements Animal, Pet {  // eat() { ? }

    public void night() {
        System.out.println("밤에 눈에서 빛이난다.");
    }

    // Animal: eat() -> Cat: eat()
    @Override
    public void eat() {
        System.out.println("고양이가 먹는다.");
    }
}
