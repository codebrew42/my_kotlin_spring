package cholog

import cholog.di.ConstructorInjection
import cholog.di.FieldInjection
import cholog.di.SetterInjection
import cholog.utils.ContextUtils.getApplicationContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext

class DependencyInjectionTest {
    @Test
    fun constructorInjection() {
        val context: ApplicationContext = getApplicationContext()
        val service = context.getBean("constructorInjection", ConstructorInjection::class.java)
        assertThat(service.sayHello()).isEqualTo("Hello")
    }

    @Test
    fun setterInjection() {
        val context: ApplicationContext = getApplicationContext()
        val service = context.getBean("setterInjection", SetterInjection::class.java)
        assertThat(service.sayHello()).isEqualTo("Hello")
    }

    @Test
    fun autowiredInjection() {
        val context: ApplicationContext = getApplicationContext()
        val service = context.getBean("fieldInjection", FieldInjection::class.java)
        assertThat(service.sayHello()).isEqualTo("Hello")
    }
}
