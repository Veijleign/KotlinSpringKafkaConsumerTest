package rui.microservices.demo.core

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import rui.microservices.demo.domain.payload.HistoryActionMessage

@Service
class TopicListener {

    @Value("\${spring.kafka.topics}")
    lateinit var topicName: String

    private val logger = KotlinLogging.logger {}

    @KafkaListener(
        topics = ["\${spring.kafka.topics}"],
        groupId = "\${spring.kafka.consumer.group-id}"
    )
    fun consume(historyActionMessage: HistoryActionMessage) {
        logger.info {
            "Topic: {$topicName}"
        }
        logger.info {
            "Received message: {$historyActionMessage}"
        }

    }
}