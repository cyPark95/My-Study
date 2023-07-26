package kr.fc.java.st_0726;

import kr.fc.utils.MyStaticUtil;
import kr.fc.utils.MyUtil;

public class ApiEx {

    public static void main(String[] args) {
        // Q. 1 ~ 10까지의 총 합을 구하고, 출력하세요.
        // Non Static Area
        MyUtil my = new MyUtil();
        int nonStaticSum = my.sumOneToTen();
        System.out.println("nonStaticSum = " + nonStaticSum);

        // Q. 1 ~ 10까지의 총 합을 구하고, 출력하세요.
        // Static Area
        int staticSum = MyStaticUtil.sum();
        System.out.println("staticSum = " + staticSum);
        // Overloading
        int overloadingSum = MyStaticUtil.sum(1, 10);
        System.out.println("overloadingSum = " + overloadingSum);

        // private 생성자로 된 API
        // Math math = new Math();
        int max = Math.max(20, 10);
        // System system = new System();
        System.out.println("Math.max(30, 50) = " + max);
    }
}
