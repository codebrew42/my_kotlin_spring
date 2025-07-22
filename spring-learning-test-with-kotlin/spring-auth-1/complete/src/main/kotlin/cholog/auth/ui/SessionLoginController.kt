package cholog.auth.ui

import cholog.auth.application.AuthService
import cholog.auth.application.AuthorizationException
import cholog.auth.dto.MemberResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpSession
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.swing.plaf.synth.Region.PASSWORD_FIELD

@RestController
class SessionLoginController(
    private val authService: AuthService,
) {
    /**
     * ex) request sample
     *
     * POST /login/session HTTP/1.1
     * content-type: application/x-www-form-urlencoded; charset=ISO-8859-1
     * host: localhost:55477
     *
     * email=email@email.com&password=1234
     */
    @PostMapping("/login/session")
    fun sessionLogin(
        request: HttpServletRequest,
        session: HttpSession,
    ): ResponseEntity<Void> {
        val paramMap = request.parameterMap
        val email = paramMap[USERNAME_FIELD]!![0]
        val password = paramMap[PASSWORD_FIELD]!![0]

        if (authService.checkInvalidLogin(email, password)) {
            throw AuthorizationException()
        }

        session.setAttribute(SESSION_KEY, email)

        return ResponseEntity.ok().build()
    }

    /**
     * ex) request sample
     *
     * GET /members/me/session HTTP/1.1
     * cookie: JSESSIONID=E7263AC9557EF658C888F02EEF840A19
     * accept: application/json
     */
    @GetMapping("/members/me/session")
    fun findMyInfo(session: HttpSession): ResponseEntity<MemberResponse> {
        val email = session.getAttribute(SESSION_KEY) as String
        val member = authService.findMember(email)
        return ResponseEntity.ok().body(member)
    }

    companion object {
        private const val SESSION_KEY = "USER"
        private const val USERNAME_FIELD = "email"
        private const val PASSWORD_FIELD = "password"
    }
}
