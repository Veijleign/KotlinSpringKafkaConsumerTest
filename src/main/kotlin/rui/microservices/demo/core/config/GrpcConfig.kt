package rui.microservices.demo.core.config

import io.github.oshai.kotlinlogging.KotlinLogging
import io.grpc.BindableService
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import lombok.extern.slf4j.Slf4j
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment



@Configuration
@Slf4j
class GrpcConfig {

    private val logger = KotlinLogging.logger {}

    @Bean
    fun grpcServer(context: ApplicationContext, env: Environment): Server {
        val builder = ServerBuilder
            .forPort(env.getRequiredProperty("grpc.server.port", Int::class.java))
            .addService(ProtoReflectionService.newInstance())

        context.getBeansOfType(BindableService::class.java)
            .values
            .forEach {
                builder.addService(it)
                logger.info {
                    "Grpc registry: ${it.javaClass.name}"
                }
            }

        val server: Server = builder.build()
        server.start()
        logger.info {
            "Grpc Server started on port: ${server.port}"
        }
        return server
    }
}