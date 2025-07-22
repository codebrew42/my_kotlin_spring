package cholog.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfiguration : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        // TODO: Serve the hello.html page when a request is made to "/"
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        // TODO: Apply LoginInterceptor to all requests matching "/admin/**"
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        // TODO: Register the AuthenticationPrincipalArgumentResolver
    }
}
