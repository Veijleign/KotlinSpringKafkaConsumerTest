package rui.microservices.demo.core.config

import lombok.Getter
import lombok.Setter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "instance")
class InstanceConfig {
    private val key: String = ""
    private val id: String = ""
}