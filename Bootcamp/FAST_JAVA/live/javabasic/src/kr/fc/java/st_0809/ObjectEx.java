package kr.fc.java.st_0809;

import kr.fc.api.MyIntArray;

public class ObjectEx {

    public static void main(String[] args) {
        // 정수형 배열에 3개의 정수를 저장하고 출력하세요.
        // 1. 배열 생성: 생성자
        int[] arr = new int[3];
        MyIntArray myArr = new MyIntArray(3);

        // 2. 배열에 데이터 저장: add()
        arr[0] = 10;
        arr[1] = 30;
        arr[2] = 20;
        myArr.add(10);
        myArr.add(30);
        myArr.add(20);

        // 3. 배열의 크기 조회: size()
        int length = arr.length;
        int size = myArr.size();

        // 4. 배열의 원소 값 조회: get()
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
        for (int i = 0; i < size; i++) {
            System.out.print(myArr.get(i) + " ");
        }
    }
}
