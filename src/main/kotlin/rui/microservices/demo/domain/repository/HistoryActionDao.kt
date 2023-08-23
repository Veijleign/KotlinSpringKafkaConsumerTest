package rui.microservices.demo.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import rui.microservices.demo.domain.entity.HistoryAction
import rui.microservices.demo.domain.payload.HistoryActionMessage
import java.time.LocalDateTime


@Component
class HistoryActionDao(
    private val coroutineHistoryActionRepository: CoroutineHistoryActionRepository
) {
    suspend fun getByServiceIdEntityNameEntityId(
        serviceId: Long,
        entityName: String,
        pageable: Pageable
    ): Page<HistoryAction> {
        return coroutineHistoryActionRepository
            .findAllByServiceInstanceIdAndEntityName(
                serviceId,
                entityName
            )
            .toPage(pageable)
    }
    suspend fun getByServiceIdAndEntityNameAndEntityId(
        serviceId: Long,
        entityName: String,
        entityId: Long,
        pageable: Pageable
    ): Page<HistoryAction> {
        return coroutineHistoryActionRepository
            .findAllByServiceInstanceIdAndEntityNameAndEntityId(
                serviceId,
                entityName,
                entityId
            )
            .toPage(pageable)
    }


    @Transactional
    suspend fun createNewRecordInDatabase(
        message: HistoryActionMessage
    ) {
        coroutineHistoryActionRepository.save(
            HistoryAction(
                null,
                message.serviceId,
                message.entityId,
                message.entityName,
                message.fieldName,
                message.pastValue,
                message.newValue,
                LocalDateTime.parse(
                    message.updatedAt
                )
            )
        )
    }

}


suspend fun Flow<HistoryAction>.toPage(pageable: Pageable): Page<HistoryAction> {
    val items = this.toList() // Collect items from the Flow

    val startIndex = pageable.pageNumber * pageable.pageSize
    val endIndex = startIndex + pageable.pageSize.coerceAtMost(items.size)
    val pageItems = items.subList(startIndex, endIndex)

    return PageImpl(
        pageItems,
        pageable,
        items
            .size
            .toLong()
    )
}

