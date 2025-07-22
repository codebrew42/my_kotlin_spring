package cholog.di

import org.springframework.stereotype.Component

@Component
class InjectionBean {
    fun hello(): String {
        return "Hello"
    }
}
