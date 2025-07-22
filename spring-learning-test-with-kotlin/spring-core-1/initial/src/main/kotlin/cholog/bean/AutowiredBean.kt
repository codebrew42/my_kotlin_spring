package cholog.bean

import org.springframework.stereotype.Component

@Component
class AutowiredBean {
    // TODO: Inject SpringBean into component using annotation
    private lateinit var springBean: SpringBean

    fun sayHello(): String {
        return springBean.hello()
    }
}
