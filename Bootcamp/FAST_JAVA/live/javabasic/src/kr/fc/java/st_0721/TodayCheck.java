package kr.fc.java.st_0721;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TodayCheck {

    public static void main(String[] args) {
        Question01();
        Question02();
        Question03();
        Question04();
        Question05();
    }

    private static void Question01() {
        // 1. 별 모양을 이중 for 문을 이용하여 출력하세요.
        // *
        // **
        // ***
        // ****
        // *****
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append("*".repeat(i + 1)).append('\n');
        }

        // *****
        // ****
        // ***
        // **
        // *
        for (int i = 0; i < 5; i++) {
            sb.append("*".repeat(5 - i)).append('\n');
        }

        //     *
        //    **
        //   ***
        //  ****
        // *****
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j < 4 - i) {
                    sb.append(" ");
                } else {
                    sb.append("*");
                }
            }
            sb.append('\n');
        }

        // *****
        //  ****
        //   ***
        //    **
        //     *
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j < i) {
                    sb.append(" ");
                } else {
                    sb.append("*");
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void Question02() {
        // 2. 이중 for 문을 이용하여 구구단을 출력하세요.
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                sb.append(j).append("x").append(i).append("=").append(j * i).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void Question03() {
        // 3. 배열에 저장된 문자를 오름차순 정렬하여 출력세요.
        char[] c = {'f', 'a', 's', 't', 'c', 'a', 'm', 'p', 'u', 's'};
        Arrays.sort(c);

        System.out.println(c);
    }

    private static void Question04() {
        // 4. 배열에 저장된 문자를 대문자로 변환하여 출력하세요.
        char[] c = {'f', 'a', 's', 't', 'c', 'a', 'm', 'p', 'u', 's'};
        int len = c.length;

        char[] upper = new char[len];
        for (int i = 0; i < len; i++) {
            upper[i] = (char) (c[i] - 32);
        }

        System.out.println(upper);
    }

    private static void Question05() {
        // 5. 배열에 저장된 수들의 빈도수(출현 회수)를 출력하세요.
        int[] a = {1, 3, 5, 4, 1, 2, 3, 1, 5, 1};
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : a) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        System.out.println(map);
    }
}
