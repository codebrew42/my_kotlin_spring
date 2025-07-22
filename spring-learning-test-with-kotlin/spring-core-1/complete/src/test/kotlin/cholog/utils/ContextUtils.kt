package cholog.utils

import cholog.SpringCoreApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object ContextUtils {
    /**
     * HelloApplication > @SpringBootApplication 설정을 통해 이미 ComponentScan 설정되어있음
     */
    fun getApplicationContext(): ApplicationContext {
        val context = AnnotationConfigApplicationContext(SpringCoreApplication::class.java)
        val beanDefinitionNames = context.beanDefinitionNames
        println(beanDefinitionNames.contentToString())
        return context
    }

    /**
     * HelloApplication > @SpringBootApplication 설정을 통해 이미 ComponentScan 설정되어있음
     */
    fun getApplicationContext(clazz: Class<*>): ApplicationContext {
        val context = AnnotationConfigApplicationContext(clazz)
        val beanDefinitionNames = context.beanDefinitionNames
        println(beanDefinitionNames.contentToString())
        return context
    }
}
