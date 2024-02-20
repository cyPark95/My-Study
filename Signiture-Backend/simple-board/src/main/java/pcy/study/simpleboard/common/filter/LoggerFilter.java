package pcy.study.simpleboard.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // 필터가 등록될 때, 호출
        log.info(">>> Register Filter");
    }

    @Override
    public void destroy() {
        // 필터가 소멸될 때, 호출
        log.info(">>> Destroy Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(">>> Filter Start");

        var cachingRequestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        var cachingResponseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        try {
            filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper);
        } finally {
            requestLogging(cachingRequestWrapper);
            responseLogging(cachingResponseWrapper);
            cachingResponseWrapper.copyBodyToResponse();
        }

        log.info(">>> Filter End");
    }

    private  void requestLogging(ContentCachingRequestWrapper request) {
        log.info(
                "[REQUEST] {} {}\n Body: {}",
                request.getMethod(),
                request.getRequestURI(),
                request.getContentAsString()
        );
    }

    private void responseLogging(ContentCachingResponseWrapper response) {
        log.info(
                "[RESPONSE] Status: {}\n Body: {}",
                response.getStatus(),
                new String(response.getContentAsByteArray())
        );
    }
}
