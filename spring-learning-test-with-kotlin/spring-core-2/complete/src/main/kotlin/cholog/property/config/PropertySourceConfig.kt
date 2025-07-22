package cholog.property.config

import cholog.property.GoogleDriveRestClient
import cholog.property.GoogleMapsRestClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment

@Configuration
@PropertySource("classpath:ext-api.properties")
class PropertySourceConfig(private val env: Environment) {
    @Bean
    fun googleMapsRestClient(): GoogleMapsRestClient {
        val endpoint =
            env.getProperty("google.api.endpoint")
                ?: throw IllegalArgumentException("Google API endpoint is not configured.")
        return GoogleMapsRestClient(endpoint)
    }

    @Bean
    fun googleDriveRestClient(
        @Value("\${google.api.endpoint}") endpoint: String,
    ): GoogleDriveRestClient {
        return GoogleDriveRestClient(endpoint)
    }
}
