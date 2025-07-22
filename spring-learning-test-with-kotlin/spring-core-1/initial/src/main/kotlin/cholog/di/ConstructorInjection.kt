package cholog.di

import org.springframework.stereotype.Service

@Service
class ConstructorInjection {
    private lateinit var injectionBean: InjectionBean

    // TODO: Inject 'InjectBean' by constructor injection
    fun sayHello(): String {
        return injectionBean.hello()
    }
}
