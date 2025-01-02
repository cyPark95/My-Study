package was;

import was.domain.HttpRequest;
import was.utils.CookieSessionUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequestParser {

    public HttpRequest parse(BufferedReader br) throws IOException {
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
        String[] headerArgs = headerLine.split(": ");
        if (headerArgs[0].startsWith("Host")) {
            request.setHost(headerArgs[1].trim());
        } else if (headerArgs[0].startsWith("Content-Length")) {
            int length = Integer.parseInt(headerArgs[1].trim());
            request.setContentLength(length);
        } else if (headerArgs[0].startsWith("Content-Type")) {
            request.setContentType(headerArgs[1].trim());
        } else if (headerArgs[0].startsWith("Cookie")) {
            String[] cookiePairs = headerArgs[1].split(";");
            String cookie = getCookie(cookiePairs);
            if (cookie != null) {
                request.setCookie(CookieSessionUtil.COOKIE_SESSION_NAME, cookie);
            }
        }
    }

    private String getCookie(String[] cookiePairs) {
        for (String cookiePair : cookiePairs) {
            String[] cookie = cookiePair.trim().split("=");
            if (cookie[0].startsWith(CookieSessionUtil.COOKIE_SESSION_NAME)) {
                return cookie[1].trim();
            }
        }

        return null;
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
