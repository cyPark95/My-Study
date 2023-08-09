package kr.fc.poly;

// 추상 클래스(불완전한 클래스)
// Bird bird = new Bird() 불가능
public abstract class Bird implements Animal {

    // name -> 상태 정보(정보 은닉)
    private String name;

    public Bird() {
    }

    public Bird(String name) {
        this.name = name;
    }

    // 1. 추상 메서드
    public abstract void fly();    // 메서드의 정의(프로토타입) -> 구현부가 없다.

    // 2. 구현 메서드
    @Override
    public void eat() {
        System.out.println("새가 먹는다.");
    }

    public String getName() {
        return name;
    }
}
