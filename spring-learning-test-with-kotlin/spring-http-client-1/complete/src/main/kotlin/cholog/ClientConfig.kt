package cholog

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate

@Configuration
class ClientConfig {
    @Bean
    fun todoClient(restTemplateBuilder: RestTemplateBuilder): TodoClientWithRestTemplate {
        val restTemplate: RestTemplate = restTemplateBuilder.build()
        return TodoClientWithRestTemplate(restTemplate)
    }

    @Bean
    fun todoRestClient(): TodoClientWithRestClient {
        return TodoClientWithRestClient(
            RestClient.builder().baseUrl("http://jsonplaceholder.typicode.com").build(),
        )
    }
}
