package kr.fc.java.st_0816;

import java.io.InputStream;

public class StreamEx {

    /*
     스트림(통로)의 분류: java.io
     1. 데이터(이진 데이터, 문자)의 종류에 따른 분류
      1) 바이트 스트림: 이진 데이터(Binary Data)를 처리하는 스트림
       - 바이트 입력 스트림: InputStream - read(): 바이트 단위로 읽기
       - 바이트 출력 스트림: OutputStream - writer(): 바이트 단위로 쓰기(출력)
         ex) 파일에서 이진 데이터를 읽기 위해 필요한 스트림은? FileInputStream
         ex) 파일에서 이진 데이터를 저장하기 위해 필요한 스트림은? FileOutputStream
      2) 문자 스트림: 문자 데이터를 처리하는 스트림(스트림 내부에서 문자 인코딩 적용된다.)
       - 문자 입력 스트림: Reader - read(): 문자 단위로 읽기
       - 문자 출력 스트림: Writer - write(), print(): 문자 단위로 쓰기
         ex) 파일에서 문자 데이터를 읽기 위해 필요한 스트림은? FileReader
         ex) 파일에서 문자 데이터를 저장하기 위해 필요한 스트림은? FileWriter

     2. 처리 방식에 따른 분류
      1) 노드 스트림: 입출력 데이터에 가장 먼저 연결되는 스트림
      2) 필터 스트림: 단독 사용 불가 -> 반드시 노드 스트림에 연결(연쇄)해서 사용 => (생성자)
         : 버퍼 기능이 있는 스트림
           - BufferedInputStream, BufferedOutputStream
           - BufferedReader, BufferedWriter
         : 브릿지 스트림
           - 바이트 입력 스트림을 문자 입력 스트림으로 변환: InputStreamReader
           - 바이트 출력 스트림을 문자 출력 스트림으로 변환: OutputStreamWriter
    */
    public static void main(String[] args) {
        // Q. 키보드로부터 영문 여러 글자를 입력해서 출력하세요.
        InputStream in = System.in;

        System.out.print("영문 여러 글자 입력: ");
        try {
            int data;
            while ((data = in.read()) != '\n') {
                System.out.print((char) data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
