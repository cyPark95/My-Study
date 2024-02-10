package was;

import was.adapter.ControllerAdepter;
import was.domain.HttpRequest;
import was.domain.HttpStatus;
import was.utils.TemplateUtil;

import java.io.*;
import java.net.Socket;

public class HttpRequestHandler {

    private final HttpRequestParser parser;
    private final ControllerAdapterFactory factory;

    public HttpRequestHandler() {
        parser = new HttpRequestParser();
        factory = new ControllerAdapterFactory();
    }

    public void mapping(Socket client) {
        try (InputStream in = client.getInputStream();
             OutputStream out = client.getOutputStream();
             client
        ) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            HttpRequest request = parser.parse(br);

            Object handler = factory.getHandler(request.getPath());
            ControllerAdepter adepter = factory.getAdapter(handler);
            if (adepter == null) {
                String responseBody = TemplateUtil.getResponseBody("[" + request.getPath() + "] 잘못된 URL 입니다.");
                out.write(TemplateUtil.httpResponse(HttpStatus.NOT_FOUND, responseBody).getBytes());
                out.flush();
                return;
            }

            out.write(adepter.handle(handler, request).getBytes());
            out.flush();
        } catch (IOException e) {
            try (OutputStream out = client.getOutputStream()){
                String responseBody = TemplateUtil.getResponseBody("서버 내부 오류가 발생했습니다.");
                out.write(TemplateUtil.httpResponse(HttpStatus.INTERNAL_SERVER_ERROR, responseBody).getBytes());
                out.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
