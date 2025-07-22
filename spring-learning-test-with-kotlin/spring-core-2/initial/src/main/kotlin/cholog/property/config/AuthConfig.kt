package cholog.property.config

import cholog.property.JwtTokenKeyProvider

// TODO: Add @Configuration annotation to the class for Spring to recognize it as a configuration class
class AuthConfig {
    // TODO: Register JwtTokenKeyProvider as a bean using the security.jwt.token.secret-key from application.properties
    fun jwtTokenKeyProvider(): JwtTokenKeyProvider {
        return JwtTokenKeyProvider("")
    }
}
