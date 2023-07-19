package kr.fc.java.st_0719;

public class TodayCheck {

    // 1. 멤버 변수
    // 2. 속성
    // 3. property
    // 4. 상태 정보
    // 초기화 하지 않아도, 기본(default) 값으로 초기화 된다.
    private int num;

    public static void main(String[] args) {
        // 문자: A(문자) -> 이진수(0, 1): A -> 65
        Question01();
        Question02();
        Question03();
        Question04();
        Question05();
    }

    private static void Question01() {
        // 1. c1 + c2 의 결과가 2가 되도록 출력하세요.
        char c1 = '1';  // '1' -> 49
        System.out.println("문자: " + c1 + " / ASCII: " + (int) c1);

        char c2 = '1';
        System.out.println((c1 - '0') + (c2 - '0'));
    }

    private static void Question02() {
        // 2. 대문자를 소문자(lower)로 출력하세요.
        char upper = 'A';  // 65(A) - 97(a): 32
        System.out.println((char) (upper + 32));
    }

    private static void Question03() {
        // 3. n1과 n2의 값을 교환(swap)하여 출력하세요.
        int n1 = 10;
        int n2 = 20;
        System.out.println("(Swap 전) n1 = " + n1 + " / n2 = " + n2);
        int tmp = n1;
        n1 = n2;
        n2 = tmp;
        System.out.println("(Swap 후) n1 = " + n1 + " / n2 = " + n2);
    }

    private static void Question04() {
        // 4. numInt 와 numDouble 을 더한 값이 53이 되도록 출력하세요.
        int numInt = 50;
        double numDouble = 3.14;
        System.out.println((int) (numInt + numDouble));
    }

    private static void Question05() {
        // 5. 어떤 예외가 발생하는가?
        // 지역변수(Local Variable)는 자동으로 초기화 되지 않는다.
//        int number;
//        System.out.println(number);
    }
}
