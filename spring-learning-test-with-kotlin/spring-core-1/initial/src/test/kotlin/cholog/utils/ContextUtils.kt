package cholog.utils

import cholog.SpringCoreApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object ContextUtils {
    fun getApplicationContext(): ApplicationContext {
        val context = AnnotationConfigApplicationContext(SpringCoreApplication::class.java)
        val beanDefinitionNames = context.beanDefinitionNames
        println(beanDefinitionNames.contentToString())
        return context
    }

    fun getApplicationContext(clazz: Class<*>): ApplicationContext {
        val context = AnnotationConfigApplicationContext(clazz)
        val beanDefinitionNames = context.beanDefinitionNames
        println(beanDefinitionNames.contentToString())
        return context
    }
}
