package cholog.di

import org.springframework.stereotype.Service

@Service
class FieldInjection {
    // TODO: Inject 'InjectBean' by field injection
    private lateinit var injectionBean: InjectionBean

    fun sayHello(): String {
        return injectionBean.hello()
    }
}
