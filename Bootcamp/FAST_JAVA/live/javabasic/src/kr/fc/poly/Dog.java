package kr.fc.poly;

public class Dog implements Animal, Pet {

    // 먹다 동작
    @Override
    public void eat() {
        System.out.println("개가 먹는다.");
    }
}
