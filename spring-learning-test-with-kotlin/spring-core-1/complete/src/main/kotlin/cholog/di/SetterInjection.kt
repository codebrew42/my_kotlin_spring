package cholog.di

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SetterInjection {
    private lateinit var injectionBean: InjectionBean

    @Autowired
    fun setInjectionBean(injectionBean: InjectionBean) {
        this.injectionBean = injectionBean
    }

    fun sayHello(): String {
        return injectionBean.hello()
    }
}
