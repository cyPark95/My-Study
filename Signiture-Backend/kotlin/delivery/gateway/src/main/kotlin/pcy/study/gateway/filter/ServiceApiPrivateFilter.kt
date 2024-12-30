package pcy.study.gateway.filter

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import pcy.study.gateway.common.Log

@Component
class ServiceApiPrivateFilter : AbstractGatewayFilterFactory<ServiceApiPrivateFilter.Config>(Config::class.java) {

    companion object: Log

    class Config

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val uri = exchange.request.uri
            log.info("Service Api Private Filter Route Uri: {}", uri)

            val mono = chain.filter(exchange)
            mono
        }
    }
}