package cholog.di

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FieldInjection {
    @Autowired
    private lateinit var injectionBean: InjectionBean

    fun sayHello(): String {
        return injectionBean.hello()
    }
}
