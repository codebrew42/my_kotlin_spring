package cholog.auth.application

import cholog.auth.dto.MemberResponse
import cholog.auth.dto.TokenRequest
import cholog.auth.dto.TokenResponse
import cholog.auth.infrastructure.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtTokenProvider: JwtTokenProvider,
) {
    fun checkInvalidLogin(
        principal: String,
        credentials: String,
    ): Boolean {
        return EMAIL != principal || PASSWORD != credentials
    }

    fun findMember(principal: String): MemberResponse {
        return MemberResponse(1L, principal, 10)
    }

    fun findMemberByToken(token: String): MemberResponse {
        val payload = jwtTokenProvider.getPayload(token)
        return findMember(payload)
    }

    fun createToken(tokenRequest: TokenRequest): TokenResponse {
        if (checkInvalidLogin(tokenRequest.email, tokenRequest.password)) {
            throw AuthorizationException()
        }

        val accessToken = jwtTokenProvider.createToken(tokenRequest.email)
        return TokenResponse(accessToken)
    }

    companion object {
        private const val EMAIL = "email@email.com"
        private const val PASSWORD = "1234"
    }
}
