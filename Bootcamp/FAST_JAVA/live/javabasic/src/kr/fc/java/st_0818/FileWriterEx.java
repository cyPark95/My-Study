package kr.fc.java.st_0818;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class FileWriterEx {

    public static void main(String[] args) {
        // 키보드로부터 책 정보를 입력 받아서 파일에 저장(book.txt)
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new FileWriter("book.txt"))
        ) {
            System.out.print("제목: ");
            String title = br.readLine();
            System.out.print("가격: ");
            int price = Integer.parseInt(br.readLine());

            bw.write(title + "," + price);
            System.out.println("파일 생성");
            // br.flush(), br.close()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
