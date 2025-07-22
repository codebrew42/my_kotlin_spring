package cholog.profile

import cholog.profile.config.ProfileConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ProfileConfigTest {
    @Test
    fun shouldCreateInmemoryRepositoryBean() {
        val context = AnnotationConfigApplicationContext()
        context.getEnvironment().setActiveProfiles("dev")
        context.register(ProfileConfig::class.java)
        context.refresh()

        val beanDefinitionNames = context.beanDefinitionNames
        println(beanDefinitionNames.contentToString())

        val service = context.getBean(MessageRepository::class.java)
        assertThat(service).isNotNull()
        assertThat(service.javaClass).isEqualTo(InmemoryMessageRepository::class.java)
    }

    @Test
    fun shouldCreateJdbcRepositoryBean() {
        val context = AnnotationConfigApplicationContext()
        context.getEnvironment().setActiveProfiles("prod")
        context.register(ProfileConfig::class.java)
        context.refresh()

        val beanDefinitionNames = context.beanDefinitionNames
        println(beanDefinitionNames.contentToString())

        val service = context.getBean(MessageRepository::class.java)
        assertThat(service).isNotNull()
        assertThat(service.javaClass).isEqualTo(JdbcMessageRepository::class.java)
    }
}
