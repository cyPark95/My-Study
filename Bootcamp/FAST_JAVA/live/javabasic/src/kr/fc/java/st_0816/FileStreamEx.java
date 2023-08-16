package kr.fc.java.st_0816;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileStreamEx {

    public static void main(String[] args) {
        // 파일 핸들링
        // 파일에 있는 데이터 읽기(이미지, 문자) - xml, json
        // 파일(file.txt)에서 문자 데이터를 읽어 오기위한 스트림 연결
        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
