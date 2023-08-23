package pcy.st_0823;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import pcy.st_0823.thread.DownloadThread;

// JVM -> Main Thread
public class ThreadEx {

    // Main Thread 실행
    public static void main(String[] args) {
        String imageUrl = "https://e7.pngegg.com/pngimages/389/1014/png-clipart-puppy-shark-dog-pet-cuteness-puppy-horse-white-thumbnail.png";
        String imageName = "shark.png";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 이미지 다운로드 -> 별도의 Thread 를 만들어서 처리
        Thread thread = new DownloadThread(imageUrl, imageName, httpClient);
        thread.start(); // run() 메서드 동작
    }
}
