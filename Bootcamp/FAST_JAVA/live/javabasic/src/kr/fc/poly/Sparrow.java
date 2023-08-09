package kr.fc.poly;

public class Sparrow extends Bird implements Pet {

    public Sparrow() {
    }

    // 부모 클래스의 상태정보를 자식 클래스에서 초기화 하면 객체지향에 위배
    // 객체 초기화는 자기 자신이 하는것이 이상적이다.
    public Sparrow(String name) {
        super(name);    // new Bird(String name);
    }

    @Override
    public void fly() {
        System.out.println("참새가 날다.");
    }

    public void sound() {
        System.out.println("짹짹");
    }
}
