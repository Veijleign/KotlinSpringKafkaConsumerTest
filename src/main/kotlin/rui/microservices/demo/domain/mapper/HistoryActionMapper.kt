//package rui.microservices.demo.domain.mapper
//
//import org.springframework.stereotype.Component
//import ru.microservices.action_history_service.HistoryActionByServiceResponse
//import ru.microservices.action_history_service.HistoryActionModel
//import ru.microservices.action_history_service.historyActionModel
//
//import rui.microservices.demo.domain.entity.HistoryAction
//
//@Component
//class HistoryActionMapper {
//
//    fun toHistoryActionModel(
//        historyAction : HistoryAction
//    ) = historyActionModel {
//        id = 1
//
//    }
//
//    fun toHistoryActionByServiceResponse(
//        historyActions: Collection<HistoryAction>
//    ) = HistoryActionByServiceResponse
//        .newBuilder()
//        .addAllHistoryActionModel(
//            historyActions
//                .map {
//                    toHistoryActionModel(it)
//                }
//                //.map(this::toHistoryActionModel)
//                .toList() // ?
//        )
//        .build()
//
//}