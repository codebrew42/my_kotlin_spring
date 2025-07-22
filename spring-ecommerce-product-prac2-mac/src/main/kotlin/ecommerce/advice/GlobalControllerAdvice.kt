package ecommerce.advice

import ecommerce.exception.NotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalControllerAdvice {
    @ExceptionHandler
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<String> {
        println("NotFoundException occurred: ${e.message}")
        return ResponseEntity.notFound().build()
    }
}
