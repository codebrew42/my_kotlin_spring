package cholog.property.config

import cholog.property.JwtTokenKeyProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthConfig {
    @Bean
    fun jwtTokenKeyProvider(
        @Value("\${security.jwt.token.secret-key}") secretKey: String,
    ): JwtTokenKeyProvider {
        return JwtTokenKeyProvider(secretKey)
    }
}
