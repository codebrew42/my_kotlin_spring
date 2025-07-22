package cholog.auth.infrastructure

import cholog.auth.dto.AuthInfo
import jakarta.servlet.http.HttpServletRequest
import org.apache.tomcat.util.codec.binary.Base64

class BasicAuthorizationExtractor : AuthorizationExtractor<AuthInfo> {
    override fun extract(request: HttpServletRequest): AuthInfo {
        val header = request.getHeader(AuthorizationExtractor.AUTHORIZATION) ?: return AuthInfo("", "")

        if (header.lowercase().startsWith(BASIC_TYPE.lowercase())) {
            val authHeaderValue = header.substring(BASIC_TYPE.length).trim()
            val decodedBytes = Base64.decodeBase64(authHeaderValue)
            val decodedString = String(decodedBytes)

            val credentials = decodedString.split(DELIMITER)
            val email = credentials[0]
            val password = credentials[1]

            return AuthInfo(email, password)
        }

        return AuthInfo("", "")
    }

    companion object {
        private const val BASIC_TYPE = "Basic"
        private const val DELIMITER = ":"
    }
}
