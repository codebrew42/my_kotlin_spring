package cholog.di

import org.springframework.stereotype.Service

@Service
class ConstructorInjection(
    private val injectionBean: InjectionBean
) {
    fun sayHello(): String {
        return injectionBean.hello()
    }
}
