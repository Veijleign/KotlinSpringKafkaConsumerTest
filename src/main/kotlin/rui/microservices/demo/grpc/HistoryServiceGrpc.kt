package rui.microservices.demo.grpc

import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import ru.microservices.action_history_service.HistoryActionByServiceAndEntityIdRequest
import ru.microservices.action_history_service.HistoryActionByServiceAndEntityNameRequest
import ru.microservices.action_history_service.HistoryActionByServiceResponse
import ru.microservices.action_history_service.HistoryActionServiceGrpcKt
import rui.microservices.demo.domain.mapper.HistoryActionMapper
import rui.microservices.demo.domain.service.HistoryActionService

@Slf4j
@RequiredArgsConstructor
@Component
class HistoryServiceGrpc(
    private val historyActionMapper: HistoryActionMapper,
    private val historyActionService: HistoryActionService
) : HistoryActionServiceGrpcKt.HistoryActionServiceCoroutineImplBase() {

    override suspend fun getHistoryActionByServiceAndEntity(
        request: HistoryActionByServiceAndEntityNameRequest
    ): HistoryActionByServiceResponse {
        return historyActionMapper.toHistoryActionByServiceResponse(
            historyActionService.getByServiceIdAndEntityName(
                request.serviceId,
                request.entityName,
                Pageable.ofSize(
                    request.count.toInt()
                )
            )
        )
    }

//    override suspend fun getHistoryActionByServiceAndEntityId(
//        request: HistoryActionByServiceAndEntityIdRequest
//    ): HistoryActionByServiceResponse {
//        return historyActionMapper.toHistoryActionByServiceResponse(
//            historyActionService.getByServiceIdAndEntityNameAndEntityId(
//                request.serviceId,
//                request.entityName,
//                request.entityId,
//                Pageable.ofSize(
//                    request.count.toInt()
//                )
//            )
//        )
//    }
}