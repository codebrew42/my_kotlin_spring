package cholog.profile.config

import cholog.profile.InmemoryMessageRepository
import cholog.profile.JdbcMessageRepository
import cholog.profile.MessageRepository

// TODO: Add @Configuration annotation to the class for Spring to recognize it as a configuration class
class ProfileConfig {
    // TODO: Register InmemoryMessageRepository as beans for "dev" profile
    fun inMemoryMessageRepository(): MessageRepository {
        return InmemoryMessageRepository()
    }

    // TODO: Register InmemoryMessageRepository as beans for "prod" profile
    fun jdbcMessageRepository(): MessageRepository {
        return JdbcMessageRepository()
    }
}
