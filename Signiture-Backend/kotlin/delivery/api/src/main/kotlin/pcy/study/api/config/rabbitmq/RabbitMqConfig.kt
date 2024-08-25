package pcy.study.api.config.rabbitmq

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig(
    @Value("\${rabbitmq.exchange}")
    val exchange: String,
    @Value("\${rabbitmq.queue}")
    val queue: String,
    @Value("\${rabbitmq.route-key}")
    val routeKey: String,
) {

    @Bean
    fun directExchange(): DirectExchange {
        return DirectExchange(exchange)
    }

    @Bean
    fun queue(): Queue {
        return Queue(queue)
    }

    @Bean
    fun binding(
        exchange: DirectExchange,
        queue: Queue
    ): Binding {
        return BindingBuilder
            .bind(queue)
            .to(exchange)
            .with(routeKey)
    }

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter
    ): RabbitTemplate {
        return RabbitTemplate().apply {
            setConnectionFactory(connectionFactory)
            setMessageConverter(messageConverter)
        }
    }

    @Bean
    fun messageConverter(
        objectMapper: ObjectMapper
    ): MessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }
}
