package cholog.bean

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AutowiredBean {
    @Autowired
    private lateinit var springBean: SpringBean

    fun sayHello(): String {
        return springBean.hello()
    }
}
