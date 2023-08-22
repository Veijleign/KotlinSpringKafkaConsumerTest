package rui.microservices.demo.domain.payload

@JvmRecord
data class HistoryActionMessage(
    val serviceId: Long,
    val entityId: Long,
    val entityName: String,
    val fieldName: String,
    val pastValue: String,
    val newValue: String,
    val updatedAt: String
)
