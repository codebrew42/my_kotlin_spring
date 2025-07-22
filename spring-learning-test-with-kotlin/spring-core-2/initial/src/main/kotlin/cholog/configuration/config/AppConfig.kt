package cholog.configuration.config

import cholog.configuration.AuthService
import cholog.configuration.AuthenticationPrincipalArgumentResolver

// TODO: Add @Configuration annotation to the class for Spring to recognize it as a configuration class
class AppConfig {
    // TODO: Register AuthService as beans
    fun authService(): AuthService {
        return AuthService()
    }

    // TODO: Register AuthenticationPrincipalArgumentResolver as beans and inject AuthService
    fun authenticationPrincipalArgumentResolver(): AuthenticationPrincipalArgumentResolver {
        return AuthenticationPrincipalArgumentResolver(authService())
    }
}
