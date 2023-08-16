package kr.fc.java.st_0816;

import java.io.InputStream;
import java.io.InputStreamReader;

public class CharacterStreamEx {

    public static void main(String[] args) {
        // Q. 한글 여러글자를 입력해서 출력하세요.
        InputStream in = System.in;

        System.out.print("한글 여러 글자 입력: ");
        int data;
        // 문자 입력 스트림: Reader
        try (InputStreamReader isr = new InputStreamReader(in)) {   // 스트림의 연결(저수준의 입출력)
                                                                    // 저수준의 입출력: 스트림 내부에서 처리해주는 동작(대표적으로 인코딩)
            while ((data = isr.read()) != '\n') {
                System.out.print((char) data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
