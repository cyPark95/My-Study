package kr.fc.poly;

public class Sparrow extends Bird {

    @Override
    public void fly() {
        System.out.println("참새가 날다.");
    }

    public void sound() {
        System.out.println("짹짹");
    }
}
