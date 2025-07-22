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

@RestController
class SessionLoginController(
    private val authService: AuthService,
) {
    /**
     * ex) request sample
     *
     POST /login/session HTTP/1.1
     content-type: application/x-www-form-urlencoded; charset=ISO-8859-1
     host: localhost:55477

     email=email@email.com&password=1234
     */
    @PostMapping("/login/session")
    fun sessionLogin(
        request: HttpServletRequest,
        session: HttpSession,
    ): ResponseEntity<Void> {
        // TODO: Extract email and password from the request parameters
        val paramMap = request.parameterMap
        val email = paramMap[USERNAME_FIELD]!![0]
        val password = paramMap[PASSWORD_FIELD]!![0]

        if (authService.checkInvalidLogin(email, password)) {
            throw AuthorizationException()
        }

        // TODO: Store the authentication information in the session (key: SESSION_KEY, value: email)
        session.setAttribute(SESSION_KEY, email)
        session.setAttribute(PASSWORD_FIELD, password)
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
        // TODO: Retrieve the email from the session using SESSION_KEY
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
