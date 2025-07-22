package cholog

import cholog.bean.AutowiredBean
import cholog.bean.SpringBean
import cholog.utils.ContextUtils.getApplicationContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext

class BeanTest {

    @Test
    fun registerBean() {
        val context: ApplicationContext = getApplicationContext()
        val springBean = context.getBean("springBean", SpringBean::class.java)
        assertThat(springBean).isNotNull
    }

    @Test
    fun autowiredBean() {
        val context: ApplicationContext = getApplicationContext()
        val autowiredBean = context.getBean("autowiredBean", AutowiredBean::class.java)
        assertThat(autowiredBean.sayHello()).isEqualTo("Hello")
    }
}
