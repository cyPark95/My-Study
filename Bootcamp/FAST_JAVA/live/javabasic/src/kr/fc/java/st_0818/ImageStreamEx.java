package kr.fc.java.st_0818;

import java.io.*;

public class ImageStreamEx {

    public static void main(String[] args) {
        // File class -> 파일, 디렉토리 생성
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("picture.png"));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("newPicture.png"))
        ) {
            int cnt = 0;
            // 0 ~ 255(Pixel)
            int data;
            while ((data = bis.read()) != -1) {
                cnt++;
                bos.write(data);
            }
            System.out.println("Byte: " + cnt);
            System.out.println("이미지 복사 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
