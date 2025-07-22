package cholog.ui

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthenticationPrincipal(val required: Boolean = true)
