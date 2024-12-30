package pcy.study.gateway.route

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pcy.study.gateway.filter.ServiceApiPrivateFilter

@Configuration
class RouteConfig(
    private val serviceApiPrivateFilter: ServiceApiPrivateFilter
) {

    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route { spec ->
                spec.order(-1)  // 우선순위
                spec.path(
                    "/service-api/api/**"   // 매칭 할 주소
                ).filters { filterSpec ->
                    filterSpec.filter(serviceApiPrivateFilter.apply(ServiceApiPrivateFilter.Config()))  // 필터 설정
                    filterSpec.rewritePath("/service-api(?<segment>/?.*)", "$\\{segment}")
                }.uri(
                    "http://localhost:8080"     // 라우팅 할 주소
                )
            }
            .build()
    }
}