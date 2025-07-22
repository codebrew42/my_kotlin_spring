package cholog.auth.ui

import cholog.auth.application.AuthService
import cholog.auth.application.AuthorizationException
import cholog.auth.dto.AuthInfo
import cholog.auth.dto.MemberResponse
import cholog.auth.infrastructure.AuthorizationExtractor
import cholog.auth.infrastructure.BasicAuthorizationExtractor
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BasicLoginController(
    private val authService: AuthService,
) {
    private val authorizationExtractor: AuthorizationExtractor<AuthInfo> = BasicAuthorizationExtractor()

    /**
     * ex) request sample
     *
     GET /members/me/basic HTTP/1.1
     authorization: Basic ZW1haWxAZW1haWwuY29tOjEyMzQ=
     accept: application/json
     */
    @GetMapping("/members/me/basic")
    fun findMyInfo(request: HttpServletRequest): ResponseEntity<MemberResponse> {
        val authInfo = authorizationExtractor.extract(request)
        val email = authInfo.email
        val password = authInfo.password

        if (authService.checkInvalidLogin(email, password)) {
            throw AuthorizationException()
        }

        val member = authService.findMember(email)
        return ResponseEntity.ok().body(member)
    }
}
