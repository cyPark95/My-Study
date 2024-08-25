package pcy.study.api.config.web

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pcy.study.api.interceptor.AuthorizationInterceptor
import pcy.study.api.resolver.UserSessionResolver

@Configuration
class WebConfig(
    private val authorizationInterceptor: AuthorizationInterceptor,
    private val userSessionResolver: UserSessionResolver
) : WebMvcConfigurer {

    private val OPEN_API: List<String> = listOf(
        "/open-api/**"
    )

    private val DEFAULT_EXCLUDE: List<String> = listOf(
        "/",
        "favicon.ico",
        "/error"
    )

    private val SWAGGER: List<String> = listOf(
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    )

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authorizationInterceptor)
            .excludePathPatterns(OPEN_API)
            .excludePathPatterns(DEFAULT_EXCLUDE)
            .excludePathPatterns(SWAGGER)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userSessionResolver)
    }
}
