package cholog.auth.dto

data class TokenRequest(
    val email: String,
    val password: String,
)
