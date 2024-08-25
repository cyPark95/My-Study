package pcy.study.api.config.health;

import lombok.extern.slf4j.Slf4j
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RequestMapping("/open-api")
class HealthOpenApiController {

    @GetMapping("/health")
    fun health() {
        log.info("Health Check")
    }
}
