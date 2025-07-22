package cholog.bean

import org.springframework.stereotype.Component

@Component
class SpringBean {
    fun hello(): String {
        return "Hello"
    }
}
