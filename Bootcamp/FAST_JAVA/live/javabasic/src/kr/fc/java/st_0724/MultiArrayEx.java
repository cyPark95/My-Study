package kr.fc.java.st_0724;

import java.util.Arrays;

public class MultiArrayEx {

    public static void main(String[] args) {
        // Q. 행과열을 가지고 있는 2차원 배열 생성, 2행 3열의 데이터를 저장하기 위한 [변수를 선언]
        int[][] arrs;
        // Q. arr 에 [2행 3열의 배열]을 생성하고, 저장하세요,
        arrs = new int[2][3];
        // Q. 모든 원소에 10을 저장하세요.
        for (int[] arr : arrs) {
            Arrays.fill(arr, 10);
        }

        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[i].length; j++) {
                System.out.println("arrs[" + i + "][" + j + "] = " + arrs[i][j]);
            }
        }
    }
}
