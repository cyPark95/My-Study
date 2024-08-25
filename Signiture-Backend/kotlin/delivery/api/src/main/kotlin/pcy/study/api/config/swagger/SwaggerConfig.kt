package pcy.study.api.config.swagger

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.core.jackson.ModelResolver
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
    info = Info(
        title = "Delivery Service API 명세서",
        description = "배달 플랫폼 API 명세서"
    )
)
@Configuration
class SwaggerConfig {

    @Bean
    fun modelResolver(
        objectMapper: ObjectMapper
    ): ModelResolver {
        return ModelResolver(objectMapper)
    }
}
