package rui.microservices.demo.domain.mapper

import org.springframework.stereotype.Component
import ru.microservices.action_history_service.HistoryActionByServiceResponse
import ru.microservices.action_history_service.historyActionModel
import rui.microservices.demo.domain.entity.HistoryAction

@Component
class HistoryActionMapper {

    fun toHistoryActionModel(
        historyAction: HistoryAction
    ) = historyActionModel {
        id = historyAction.id!!
        serviceInstanceId = historyAction.serviceInstanceId!!
        entityId = historyAction.entityId!!
        entityName = historyAction.entityName!!
        fieldName = historyAction.fieldName!!
        newValueName = historyAction.newValue!!
        pastValueName = historyAction.pastValue!!
        updatedAt = historyAction.updatedAt.toString()
    }

    fun toHistoryActionByServiceResponse(
        historyActions: Collection<HistoryAction>
    ): HistoryActionByServiceResponse =
        HistoryActionByServiceResponse
            .newBuilder()
            .addAllHistoryActionModel(
                historyActions
                    .map(::toHistoryActionModel)
                //.map(this::toHistoryActionModel)
            )
            .build()


}