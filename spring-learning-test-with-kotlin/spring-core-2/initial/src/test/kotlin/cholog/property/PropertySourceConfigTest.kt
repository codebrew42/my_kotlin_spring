package cholog.property

import cholog.property.config.PropertySourceConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class PropertySourceConfigTest {
    @Test
    fun getPropertyValueFromUsingEnvironment() {
        val context: ApplicationContext = AnnotationConfigApplicationContext(PropertySourceConfig::class.java)
        val beanDefinitionNames = context.beanDefinitionNames
        println(beanDefinitionNames.contentToString())

        val restClient: RestClient = context.getBean(GoogleMapsRestClient::class.java)
        assertThat(restClient.getEndpoint()).isEqualTo("https://www.googleapis.com")
    }

    @Test
    fun getPropertyValueFromUsingAnnotation() {
        val context: ApplicationContext = AnnotationConfigApplicationContext(PropertySourceConfig::class.java)
        val beanDefinitionNames = context.beanDefinitionNames
        println(beanDefinitionNames.contentToString())

        val restClient: RestClient = context.getBean(GoogleDriveRestClient::class.java)
        assertThat(restClient.getEndpoint()).isEqualTo("https://www.googleapis.com")
    }
}
