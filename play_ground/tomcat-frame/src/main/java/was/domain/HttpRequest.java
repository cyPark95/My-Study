package was.domain;

public class HttpRequest {

    private final String method;
    private final String path;
    private final String queryString;
    private String host;
    private int contentLength;
    private String contentType;
    private Cookie cookie;
    private char[] body;

    public boolean isQueryString() {
        return queryString != null;
    }

    public boolean isCookie() {
        return cookie != null;
    }

    public boolean isBody() {
        return contentType != null || contentLength > 0;
    }

    public HttpRequest(String method, String path) {
        this(method, path, null);
    }

    public HttpRequest(String method, String path, String queryString) {
        this.method = method;
        this.path = path;
        this.queryString = queryString;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setCookie(String name, String value) {
        cookie = new Cookie(name, value);
    }

    public void setBody(char[] body) {
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getHost() {
        return host;
    }

    public int getContentLength() {
        return contentLength;
    }

    public String getContentType() {
        return contentType;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public String getBody() {
        if (body == null) {
            return "";
        }
        return new String(body);
    }
}
