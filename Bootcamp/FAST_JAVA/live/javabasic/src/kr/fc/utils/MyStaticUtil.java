package kr.fc.utils;

public class MyStaticUtil {

    private MyStaticUtil() {

    }

    // Q. 1 ~ 10 까지의 총 합을 구하세요.(static zone)
    public static int sum() {
        int sum = 0;
        for (int i = 1; i < 11; i++) {
            sum += i;
        }

        return sum;
    }

    // Q. a ~ b 까지의 총 합을 구하세요.(static zone)
    public static int sum(int a, int b) {
        int sum = 0;
        for (int i = a; i < b + 1; i++) {
            sum += i;
        }

        return sum;
    }
}
