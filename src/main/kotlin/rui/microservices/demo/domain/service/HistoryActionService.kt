package rui.microservices.demo.domain.service

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.*
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Pageable
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.stereotype.Service
import rui.microservices.demo.domain.entity.HistoryAction
import rui.microservices.demo.domain.payload.HistoryActionMessage
import rui.microservices.demo.domain.repository.HistoryActionDao

@Service
@AllArgsConstructor
@Slf4j
class HistoryActionService(
    private val historyActionDao: HistoryActionDao
) {

    private val logger = KotlinLogging.logger {}

    fun getByServiceIdAndEntityName(
        serviceId: Long,
        entityName: String,
        pageable: Pageable

    ): List<HistoryAction> {
        return runBlocking {
            historyActionDao.getByServiceIdEntityNameEntityId(
                serviceId,
                entityName,
                pageable
            ).toList()
        }
    }

        fun getByServiceIdAndEntityNameAndEntityId(
        serviceId: Long,
        entityName: String,
        entityId: Long,
        pageable: Pageable

    ): List<HistoryAction> {
            return runBlocking {
                historyActionDao.getByServiceIdAndEntityNameAndEntityId(
                    serviceId,
                    entityName,
                    entityId,
                    pageable
                ).toList()
            }
    }

    @KafkaListener(
        topics = ["\${spring.kafka.topics}"],
        groupId = "\${spring.kafka.consumer.group-id}"
    )
    fun consume(
        historyActionMessage: HistoryActionMessage,
        @org.springframework.messaging.handler.annotation.Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int
    ) {
        logger.info {
            "From partition: $partition\n"
        }

        CoroutineScope(Dispatchers.IO).launch { // хватит ли мощностей Dispatchers.IO?
            logger.info {
                "I'm working in thread: ${Thread.currentThread().name}" +
                        "Received message: {$historyActionMessage}"

            }

            historyActionDao.createNewRecordInDatabase(
                historyActionMessage
            )
        }
    }

}