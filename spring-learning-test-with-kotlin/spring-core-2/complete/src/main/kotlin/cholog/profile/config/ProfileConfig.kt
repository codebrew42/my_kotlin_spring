package cholog.profile.config

import cholog.profile.InmemoryMessageRepository
import cholog.profile.JdbcMessageRepository
import cholog.profile.MessageRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ProfileConfig {
    @Bean
    @Profile("dev")
    fun inMemoryMessageRepository(): MessageRepository {
        return InmemoryMessageRepository()
    }

    @Bean
    @Profile("prod")
    fun jdbcMessageRepository(): MessageRepository {
        return JdbcMessageRepository()
    }
}
