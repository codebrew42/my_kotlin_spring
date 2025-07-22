package cholog.auth.infrastructure

import jakarta.servlet.http.HttpServletRequest

class BearerAuthorizationExtractor : AuthorizationExtractor<String> {
    override fun extract(request: HttpServletRequest): String {
        val headers = request.getHeaders(AuthorizationExtractor.AUTHORIZATION)
        while (headers.hasMoreElements()) {
            val value = headers.nextElement()
            if (value.lowercase().startsWith(BEARER_TYPE.lowercase())) {
                var authHeaderValue = value.substring(BEARER_TYPE.length).trim()
                request.setAttribute(ACCESS_TOKEN_TYPE, value.substring(0, BEARER_TYPE.length).trim())
                val commaIndex = authHeaderValue.indexOf(',')
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex)
                }
                return authHeaderValue
            }
        }

        return ""
    }

    companion object {
        private const val BEARER_TYPE = "Bearer"
        private val ACCESS_TOKEN_TYPE = "${BearerAuthorizationExtractor::class.java.simpleName}.ACCESS_TOKEN_TYPE"
    }
}
