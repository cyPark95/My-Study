package was;

import was.adapter.ControllerAdepter;
import was.domain.HttpRequest;
import was.domain.HttpStatus;
import was.utils.HtmlTemplate;

import java.io.*;
import java.net.Socket;

public class HttpRequestHandler {

    private final ControllerAdapterFactory factory;

    public HttpRequestHandler() {
        factory = new ControllerAdapterFactory();
    }

    public void mapping(Socket client) {
        try (InputStream in = client.getInputStream();
             OutputStream out = client.getOutputStream();
             client
        ) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            HttpRequest request = getHttpRequestInfo(br);

            Object handler = factory.getHandler(request.getPath());
            ControllerAdepter adepter = factory.getAdapter(handler);
            if (adepter == null) {
                out.write(HtmlTemplate.response(HttpStatus.NOT_FOUND, "[" + request.getPath() + "] 잘못된 URL 입니다.").getBytes());
                out.flush();
                return;
            }

            String responseBody = adepter.handle(handler, request);
            out.write(HtmlTemplate.response(HttpStatus.OK, responseBody).getBytes());
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest getHttpRequestInfo(BufferedReader br) throws IOException {
        HttpRequest request = getHeaderInfo(br);

        if (request.isBody()) {
            request.setBody(getBodyInfo(request.getContentLength(), br));
        }
        return request;
    }

    private HttpRequest getHeaderInfo(BufferedReader br) throws IOException {
        String firstLine = br.readLine();
        HttpRequest request = getFirstHeaderLineInfo(firstLine);

        String line;
        while ((line = br.readLine()) != null) {
            if ("".equals(line)) {
                break;
            }
            getHeaderInfo(request, line);
        }

        return request;
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

    private char[] getBodyInfo(int contentLength, BufferedReader br) throws IOException {
        char[] requestBody = new char[contentLength];
        int bytesRead = 0;
        while (bytesRead < contentLength) {
            int read = br.read(requestBody, bytesRead, contentLength - bytesRead);
            if (read == -1) {
                break;
            }
            bytesRead += read;
        }
        return requestBody;
    }
}
