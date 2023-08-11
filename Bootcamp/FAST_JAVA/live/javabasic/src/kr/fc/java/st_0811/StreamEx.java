package kr.fc.java.st_0811;

import java.io.InputStream;

public class StreamEx {

    public static void main(String[] args) {
        // 표준 입출력 방식(파일, 메모리, 네트워크)
        // 키보드로 부터 데이터를 입력 받는 것: 표준 입력
        // 스트림(Stream) - 통로 역할: 객체로 취급
        // 스트림 -> Stream(X): InputStream(입력) - read(), OutputStream(출력) - write(), print()
        InputStream in = System.in;
        System.out.println("영문 한 글자 입력: ");
        try {
            int data = in.read();   // IOException 이 발생할 수 있다.
            // PrintStream out = System.out;
            System.out.println((char) data);
        } catch (Exception e) {     // 예외 다형성
            e.printStackTrace();    // 예외 메시지 출력
        }
    }
}
