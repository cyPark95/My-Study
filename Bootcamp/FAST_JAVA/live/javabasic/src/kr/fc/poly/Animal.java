package kr.fc.poly;

public interface Animal {
    // 서로 다른 객체의 공통 부분을 묶을 때 사용한다.
    // 객체를 생성할 수 없다.
    // 부모 타입의 역할로 사용할 수 있다.(UpCasting)
    // 다중 상속을 지원하기 위해서 사용된다.
    // 다중상속을 지원하기 위해 사용된다.

    default void eat() {
        System.out.println("?");
    }
}
