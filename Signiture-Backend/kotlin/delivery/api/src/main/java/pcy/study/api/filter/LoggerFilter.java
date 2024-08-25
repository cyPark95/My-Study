package pcy.study.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        var responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        try {
            requestLogging(requestWrapper);
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            responseLogging(responseWrapper);
        }

        responseWrapper.copyBodyToResponse();
    }

    private void requestLogging(ContentCachingRequestWrapper request) {
        var uri = request.getRequestURI();
        var method = request.getMethod();
        Collection<String> headerNames = Collections.list(request.getHeaderNames());
        var requestHeader = getHeaderValues(headerNames, request::getHeader);
        var requestBody = request.getContentAsString();
        log.info("[REQUEST] {} {}\n[HEADER]\n{}\n[BODY]\n{}", method, uri, requestHeader, requestBody);
    }

    private void responseLogging(ContentCachingResponseWrapper response) {
        Collection<String> headerNames = response.getHeaderNames();
        var responseHeader = getHeaderValues(headerNames, response::getHeader);
        var responseBody = new String(response.getContentAsByteArray());
        log.info("[RESPONSE]\n[HEADER]\n{}\n[BODY]\n{}", responseHeader, responseBody);
    }

    private String getHeaderValues(Collection<String> headerNames, Function<String, String> headerGetter) {
        var headerValues = new StringBuilder();
        headerNames.forEach(headerKey -> {
            var headerValue = headerGetter.apply(headerKey);
            headerValues.append(headerKey).append(" : ").append(headerValue).append("\n");
        });
        return headerValues.toString();
    }
}
