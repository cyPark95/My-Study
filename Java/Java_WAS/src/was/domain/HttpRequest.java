package was.domain;

public class HttpRequest {

    private final String method;
    private final String path;
    private final String queryString;
    private String host;
    private int contentLength;
    private String contentType;
    private char[] body;

    public boolean isQueryString() {
        return queryString != null;
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

    public String getBody() {
        return new String(body);
    }
}
