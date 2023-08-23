package pcy.st_0823.thread;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;

public class DownloadRunnable implements Runnable {

    @Override
    public void run() {
        String imageUrl = "https://item.kakaocdn.net/do/9d4a4d8368feb0ea31d42724f36156f59f5287469802eca457586a25a096fd31";
        String imageName = "bonobono.png";

        HttpGet httpGet = new HttpGet(imageUrl);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet);
             FileOutputStream fos = new FileOutputStream(imageName)
        ) {
            HttpEntity entity = response.getEntity();

            byte[] ingBuf = EntityUtils.toByteArray(entity);
            fos.write(ingBuf);
            System.out.println("이미지 다운로드 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
