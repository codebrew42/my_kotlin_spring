package cholog.configuration.config

import cholog.configuration.AuthService
import cholog.configuration.AuthenticationPrincipalArgumentResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun authService(): AuthService {
        return AuthService()
    }

    @Bean
    fun authenticationPrincipalArgumentResolver(): AuthenticationPrincipalArgumentResolver {
        return AuthenticationPrincipalArgumentResolver(authService())
    }
}
