package cholog

import cholog.scan.ComponentScanBean
import cholog.scan.ContextConfiguration
import cholog.utils.ContextUtils.getApplicationContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext

class ComponentScanTest {

    @Test
    fun scanComponent() {
        val context: ApplicationContext = getApplicationContext(ContextConfiguration::class.java)
        val componentScanBean = context.getBean("componentScanBean", ComponentScanBean::class.java)
        assertThat(componentScanBean).isNotNull
    }
}
