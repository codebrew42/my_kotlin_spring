package cholog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringClientApplication

fun main(args: Array<String>) {
    runApplication<SpringClientApplication>(*args)
}
