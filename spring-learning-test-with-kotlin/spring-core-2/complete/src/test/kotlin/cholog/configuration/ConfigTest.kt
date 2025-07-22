package cholog.configuration

import cholog.configuration.config.AppConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConfigTest {
    @Test
    fun shouldCreateBean() {
        val context: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val beanDefinitionNames = context.beanDefinitionNames
        println(beanDefinitionNames.contentToString())

        val authService = context.getBean(AuthService::class.java)
        assertThat(authService).isNotNull()
    }

    @Test
    fun shouldCreateBeansThatHasDependency() {
        val context: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val beanDefinitionNames = context.beanDefinitionNames
        println(beanDefinitionNames.contentToString())

        val resolver =
            context.getBean(AuthenticationPrincipalArgumentResolver::class.java)
        assertThat(resolver).isNotNull()
        assertThat(resolver.authService).isNotNull()

        val authService = context.getBean(AuthService::class.java)
        assertThat(resolver.authService).isEqualTo(authService)
    }
}
