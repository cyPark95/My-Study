package kr.fc.java.st_0726;

public class StringEx {

    public static void main(String[] args) {
        // 자바에서 문자열 처리 => 객체로 취급
        String strObject = new String("APPLE");
        System.out.println(strObject);  // s.toString() => String 클래스에 toString() 메서드 오버라이드(Override)

        // str 에는 "APPLE"(문자열 상수)의 주소값 저장
        String str = "APPLE";

        // 1. 문자열 길이 출력
        System.out.println("length() = " + strObject.length());

        // 2. 모두 소문자 출력
        System.out.println("toLowerCase() = " + strObject.toLowerCase());

        // 3. "PL" 부분 문자열만 출력
        System.out.println("substring() = " + strObject.substring(2, 4));

        // 4. "#"을 기준으로 문자열 분리
        String fruitStr = "바나나#포도#오렌지#귤";
        String[] fruits = fruitStr.split("#");
        for (String fruit : fruits) {
            System.out.println("split() = " + fruit);
        }

        // 5. 문자열 비교(같다, 다르다)
        System.out.println("equals() = " + strObject.equals(str));

        // 6. 문자열 비교(크다, 작다) => ASCII 코드 비교
        // 음수 => 작다, 양수 => 크다, 0 => 같다
        String color = "BLUE";
        System.out.println("compareTo() = " + strObject.compareTo(color));
    }
}
