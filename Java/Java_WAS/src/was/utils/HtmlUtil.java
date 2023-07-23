package was.utils;

import was.HttpRequest;

public class HtmlUtil {

    private HtmlUtil() {
    }

    public static String responseTemplate(HttpRequest request) {
        String responseBody = getResponseBody(request);

        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length: " + responseBody.length() + "\r\n"
                + "\r\n"
                + responseBody;
    }

    private static String getResponseBody(HttpRequest request) {
        return "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "   <head>"
                + "       <meta charset=\"UTF-8\">"
                + "       <title>My WAS</title>"
                + "   </head>"
                + "   <body>"
                + "       <h3>Hello, HttpServer!!!</h3>"
                + getRequestContents(request)
                + "   </body>"
                + "</html>";
    }

    private static String getRequestContents(HttpRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("       <h5>REQUEST INFO</h5>").append('\n');
        sb.append("       <span>Method: ").append(request.getMethod()).append("</span></br>").append('\n');
        sb.append("       <span>HOST: ").append(request.getHost()).append("</span></br>").append('\n');
        sb.append("       <span>PATH: ").append(request.getPath()).append("</span></br>").append('\n');
        if (request.isQueryString()) {
            sb.append("       <span>QueryString: ").append(request.getQueryString()).append("</span></br>").append('\n');
        }
        if (request.isContentType()) {
            sb.append(getRequestBodyContents(request));
        }
        return sb.toString();
    }

    private static String getRequestBodyContents(HttpRequest request) {
        return "       <span>ContentLength: " + request.getContentLength() + " / ContentType: " + request.getContentType() + "</span></br>\n"
                + "       <span>RequestBody: " + request.getBody() + "</span></br>\n";
    }
}
