package rui.microservices.demo.grpc

import org.springframework.stereotype.Component
import ru.microservices.demo.*

@Component
class GrpcService : HelloServiceGrpcKt.HelloServiceCoroutineImplBase() {

    override suspend fun hello(
        request: HelloRequest
    ) = helloResponse {
        fullResponse = "Hello from Kotlin, ${request.name}!"
    }

    override suspend fun moreComplicated(
        request: MoreComplicatedRequest
    ): MoreComplicatedResponse {
        return MoreComplicatedResponse
            .newBuilder()
            .setUsername("GOMINID")
            .setName("Al'bertovich")
            .setDescription("Loves cooking pancakes")
            .setRole("User")
            .setIsActive(true)
            .addAllPermissions(listOf("READ", "WRITE", "DELETE"))
            .build()

    }

}
