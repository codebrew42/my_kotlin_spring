package cholog.di

import org.springframework.stereotype.Service

@Service
class SetterInjection {
    private lateinit var injectionBean: InjectionBean

    // TODO: Inject 'InjectBean' by setter injection
    fun sayHello(): String {
        return injectionBean.hello()
    }
}
