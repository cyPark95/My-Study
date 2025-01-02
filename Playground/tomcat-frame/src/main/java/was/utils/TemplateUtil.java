package was.utils;

import was.domain.Cookie;
import was.domain.HttpRequest;
import was.domain.HttpStatus;

public class TemplateUtil {

    private TemplateUtil() {
    }

    public static String httpResponse(HttpStatus status, String responseBody) {
        return "HTTP/1.1 " + status.getValue() + "\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length: " + responseBody.length() + "\r\n"
                + "\r\n"
                + responseBody;
    }

    public static String httpResponseWithCookie(HttpStatus status,
                                                Cookie cookie,
                                                String responseBody
    ) {
        return "HTTP/1.1 " + status.getValue() + "\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length: " + responseBody.length() + "\r\n"
                + "Set-Cookie: " + cookie.name() + "=" + cookie.value() + "\r\n"
                + "\r\n"
                + responseBody;
    }

    public static String getBasicResponseBody(HttpRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("       <h5>REQUEST INFO</h5>").append('\n');
        sb.append("       <span>Method: ").append(request.getMethod()).append("</span></br>").append('\n');
        sb.append("       <span>HOST: ").append(request.getHost()).append("</span></br>").append('\n');
        sb.append("       <span>PATH: ").append(request.getPath()).append("</span></br>").append('\n');
        if (request.isCookie()) {
            Cookie cookie = request.getCookie();
            sb.append("       <span>Cookie: ").append(cookie.name()).append("=").append(cookie.value()).append("</span></br>").append('\n');
            sb.append("       <span>Session: ").append(CookieSessionUtil.getSession(cookie.value())).append("</span></br>").append('\n');
        }
        if (request.isQueryString()) {
            sb.append("       <span>QueryString: ").append(request.getQueryString()).append("</span></br>").append('\n');
        }
        if (request.isBody()) {
            sb.append("       <span>ContentLength: ").append(request.getContentLength())
                    .append(" / ContentType: ").append(request.getContentType()).append("</span></br>\n")
                    .append("       <span>RequestBody: ").append(request.getBody()).append("</span></br>\n");
        }

        return getResponseBody(sb.toString());
    }

    public static String getResponseBody(String bodyMessage) {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "   <head>\n"
                + "       <meta charset=\"UTF-8\">\n"
                + "       <title>My WAS</title>\n"
                + "   </head>\n"
                + "   <body>\n"
                + "       <h3>Hello, HttpServer!!!</h3>\n"
                + "       <div>\n"
                + bodyMessage + "\n"
                + "       </div>\n"
                + "   </body>\n"
                + "</html>";
    }
}
