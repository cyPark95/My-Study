package kr.fc.java.st_0724;

import java.util.Arrays;

public class StaticEx {

    public static void main(String[] args) {
        // 변수, 배열, 메서드
        // 배열선언, 생성, 초기화
        // 3개의 정수(10, 20, 30)를 배열에 [초기화] 하세요. => { ... }
        int[] arr = {10, 20, 30};   // []: 1차원 배열 -> Vector, List, Array ...
                                    // [][]: 2차원 배열 -> table, matrix(행렬) ...

        // static zone 에 저장
        int total = total(arr);     // 매개변수 전달 기법(번지 전달: Call By Reference)
        System.out.println(total);

        // non-static zone 에 저장
        int sum = new StaticEx().sum(10, 30);      // 매개변수 전달 기법(값 전달: Call By Value)
    }

    // Q. 매개변수로 [정수 여러개]를 받아서 [총합]을 구하여 [리턴]하는 메서드를 정의하세요.
    private static int total(int[] arr) {
        return Arrays.stream(arr).sum();
    }

    public int sum(int n1, int n2) {
        return n1 + n2;
    }
}
