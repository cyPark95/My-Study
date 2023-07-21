package kr.fc.java.st_0721;

public class ArrayEx {

    public static void main(String[] args) {
        // 변수는 데이터를 1개의 형태로 저장한다.
        // Q. [정수] [3개]를 저장할 [변수를 선언]하세요.
        // int: 정수 한개
        // int[] : 정수 여러개
        int[] arr;

        // Q. 3개의 정수를 저장할 수 있는 [배열을 생성]하세요.
        // 배열 => 클래스
        arr = new int[3];

        // Q. 요10, 20, 30을 대입하세요.
        arr[0] = 10;
        arr[1] = 20;
        arr[2] = 30;

        // Q. 총합을 출력하는 메서드를 만드세요.
        total(arr);
    }

    private static void total(int[] arr) {
        int total = 0;
        // 반복문 활용(for, while, foreach ...)
        // Q. for 문을 사용해서 총 합을 구하세요.
        for (int n : arr) {
            total += n;
        }

        System.out.println("total = " + total);
    }
}
