package rui.microservices.demo.domain.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import rui.microservices.demo.domain.entity.HistoryAction

@Repository
interface CoroutineHistoryActionRepository :
    CoroutineCrudRepository<HistoryAction, Long> {

    suspend fun findAllByServiceInstanceIdAndEntityName(
        serviceId: Long,
        entityName: String
    ) : Flow<HistoryAction>

    suspend fun findAllByServiceInstanceIdAndEntityNameAndEntityId(
        serviceId: Long,
        entityName: String,
        entityId: Long

    ) : Flow<HistoryAction>

}