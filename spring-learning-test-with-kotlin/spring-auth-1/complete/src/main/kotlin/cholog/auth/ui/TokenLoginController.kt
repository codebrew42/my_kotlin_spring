package cholog.auth.ui

import cholog.auth.application.AuthService
import cholog.auth.dto.MemberResponse
import cholog.auth.dto.TokenRequest
import cholog.auth.dto.TokenResponse
import cholog.auth.infrastructure.AuthorizationExtractor
import cholog.auth.infrastructure.BearerAuthorizationExtractor
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenLoginController(
    private val authService: AuthService,
) {
    private val authorizationExtractor: AuthorizationExtractor<String> = BearerAuthorizationExtractor()

    /**
     * ex) request sample
     *
     * POST /login/token HTTP/1.1
     * accept: application/json
     * content-type: application/json; charset=UTF-8
     *
     * {
     * "email": "email@email.com",
     * "password": "1234"
     * }
     */
    @PostMapping("/login/token")
    fun tokenLogin(
        @RequestBody tokenRequest: TokenRequest,
    ): ResponseEntity<TokenResponse> {
        val tokenResponse = authService.createToken(tokenRequest)
        return ResponseEntity.ok().body(tokenResponse)
    }

    /**
     * ex) request sample
     *
     * GET /members/me/token HTTP/1.1
     * authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBlbWFpbC5jb20iLCJpYXQiOjE2MTAzNzY2NzIsImV4cCI6MTYxMDM4MDI3Mn0.Gy4g5RwK1Nr7bKT1TOFS4Da6wxWh8l97gmMQDgF8c1E
     * accept: application/json
     */
    @GetMapping("/members/me/token")
    fun findMyInfo(request: HttpServletRequest): ResponseEntity<MemberResponse> {
        val token = authorizationExtractor.extract(request)
        val member = authService.findMemberByToken(token)
        return ResponseEntity.ok().body(member)
    }
}
