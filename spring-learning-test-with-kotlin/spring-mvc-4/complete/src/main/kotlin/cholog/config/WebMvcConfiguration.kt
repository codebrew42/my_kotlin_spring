package cholog.config

import cholog.ui.AuthenticationPrincipalArgumentResolver
import cholog.ui.CheckLoginInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfiguration : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/").setViewName("hello")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(CheckLoginInterceptor())
            .addPathPatterns("/admin/**")
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(AuthenticationPrincipalArgumentResolver())
    }
}
