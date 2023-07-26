package kr.fc.utils;

public class MyUtil {

    // Q. 1 ~ 10까지의 총 합을 구하세요.(non static zone)
    public int sumOneToTen() {
        int sum = 0;
        for (int i = 1; i < 11; i++) {
            sum += i;
        }

        return sum;
    }
}
