package cholog.configuration

class AuthenticationPrincipalArgumentResolver(val authService: AuthService) {
    fun findMemberName(): String {
        return authService.findMemberName()
    }
}
