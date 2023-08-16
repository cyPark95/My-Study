package kr.fc.java.st_0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferedStreamEx {

    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);

        System.out.print("문자열 입력: ");
        // 버퍼 기능이 있는 문자 입력 스트림
        try (BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {  // 라인 단위로 읽기 가능(엔터를 제외한 문자열 읽기)
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
