package was;

import java.io.*;
import java.net.Socket;
import was.utils.HtmlUtil;

public class HttpRequestHandler {

    public void printRequestInfo(Socket client) {
        try (InputStream in = client.getInputStream();
             OutputStream out = client.getOutputStream();
             client
        ) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line = br.readLine();
            if (line == null) {
                return;
            }
            HttpRequest request = getFirstHeaderLineInfo(line);

            // 헤더 값 세팅
            while ((line = br.readLine()) != null) {
                if ("".equals(line)) {
                    break;
                }
                getHeaderInfo(request, line);
            }

            // 메시지 바디 값 조회
            if (request.getContentLength() > 0) {
                int len = request.getContentLength();

                char[] requestBody = new char[len];
                int bytesRead = 0;
                while (bytesRead < len) {
                    int read = br.read(requestBody, bytesRead, len - bytesRead);
                    if (read == -1) {
                        break;
                    }
                    bytesRead += read;
                }
                request.setBody(requestBody);
            }

            System.out.println(request);

            String httpResponse = HtmlUtil.responseTemplate(request);
            out.write(httpResponse.getBytes());
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest getFirstHeaderLineInfo(String firstLine) {
        String[] firstLineArgs = firstLine.split(" ");

        if (firstLineArgs[1].indexOf("?") > 0) {
            String[] path = firstLineArgs[1].split("\\?");
            if (path.length > 1) {
                return new HttpRequest(firstLineArgs[0], path[0], path[1]);
            }

            return new HttpRequest(firstLineArgs[0], path[0]);
        }

        return new HttpRequest(firstLineArgs[0], firstLineArgs[1]);
    }

    private void getHeaderInfo(HttpRequest request, String headerLine) {
        String[] headerArgs = headerLine.split(" ");
        if (headerArgs[0].startsWith("Host")) {
            request.setHost(headerArgs[1].trim());
        } else if (headerArgs[0].startsWith("Content-Length")) {
            int length = Integer.parseInt(headerArgs[1].trim());
            request.setContentLength(length);
        } else if (headerArgs[0].startsWith("Content-Type")) {
            request.setContentType(headerArgs[1].trim());
        }
    }
}
