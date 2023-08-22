package rui.microservices.demo.core.config


import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

import org.springframework.kafka.support.serializer.JsonDeserializer
import rui.microservices.demo.domain.payload.HistoryActionMessage


@EnableKafka
@Configuration
class KafkaConsumerConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    lateinit var server: String

    @Value("\${spring.kafka.consumer.group-id}")
    lateinit var groupId: String


    @Bean
    fun consumerFactory():
            ConsumerFactory<String, HistoryActionMessage> {

        val props: MutableMap<String, Any> = HashMap()

        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = server
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        props[JsonDeserializer.USE_TYPE_INFO_HEADERS] = false
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java.name
        props[JsonDeserializer.VALUE_DEFAULT_TYPE] = HistoryActionMessage::class.java.name

        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory():
            ConcurrentKafkaListenerContainerFactory<String, HistoryActionMessage> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, HistoryActionMessage> =
            ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()

//        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
//        factory.containerProperties.isSyncCommits = true;

        return factory

    }

}