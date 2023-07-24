package kr.fc.java.st_0724;

public class MethodEx {

    public static void main(String[] args) {
        // 데이터의 개수에 따른 설명: 변수 -> 배열(동일한 자료), 기본배열(int[]), 객체배열(Book[])
        // 자료의 종류에 따른 설명: 배열(동일한 자료) -> 클래스(이질적인 자료)
        // 변수와 메서드의 관계: 데이터를 하나만 저장하는 부분에서는 동일한 컨샙

        int n1 = 10, n2 = 20;
        System.out.println(n1 + n2);  // 30
        // sum 메서드를 호출하는 문장
        MethodEx method = new MethodEx();
        System.out.println(method.sum(n1, n2));
    }

    // 메서드(동작, 방법, 기능, 함수): sum(메서드의 이름이 변수 역할)
    public int sum(int n1, int n2) {
        // Q. 두개의 정수를 외부로 부터 받아서 총합을 구하세요.
        return n1 + n2;
    }
}
