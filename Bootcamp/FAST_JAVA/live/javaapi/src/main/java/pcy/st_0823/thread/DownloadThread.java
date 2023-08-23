package pcy.st_0823.thread;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;

@Getter
@AllArgsConstructor
public class DownloadThread extends Thread {    // 스레드를 처리하는 작업(Task) 클래스

    private final String imageUrl;
    private final String imageName;
    private final CloseableHttpClient httpClient;

    public void run() {
        HttpGet httpGet = new HttpGet(imageUrl);                            // 연결
        try (CloseableHttpResponse response = httpClient.execute(httpGet);  // 실행(요청)
             FileOutputStream fos = new FileOutputStream(imageName)
        ) {
            HttpEntity entity = response.getEntity();

            // 버퍼에 저장한 후에 다운로드
            byte[] ingBuf = EntityUtils.toByteArray(entity);
            fos.write(ingBuf);
            System.out.println("이미지 다운로드 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
