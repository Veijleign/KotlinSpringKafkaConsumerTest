//package rui.microservices.demo.domain.entity
//
//import lombok.AllArgsConstructor
//import lombok.Getter
//import lombok.NoArgsConstructor
//import lombok.Setter
//import org.springframework.data.annotation.Id
//import org.springframework.data.relational.core.mapping.Column;
//import org.springframework.data.relational.core.mapping.Table;
//import java.time.LocalDateTime
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Table("history_action")
//data class HistoryAction(
//    @Id
//    val id : Long? = null,
//    @Column("service_instance_id")
//    val serviceInstanceId: Long? = null,
//
//    @Column("entity_id")
//    val entityId: Long? = null,
//
//    @Column("entity_name")
//    val entityName: String? = null,
//
//    @Column("field_name")
//    val fieldName: String? = null,
//
//    @Column("past_value")
//    val pastValue: String? = null,
//
//    @Column("new_value")
//    val newValue: String? = null,
//
//    @Column("updated_at")
//    val updatedAt: LocalDateTime = LocalDateTime.now()
//
//)
