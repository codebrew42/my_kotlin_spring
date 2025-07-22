# 1. Exception Handling in Spring MVC

<br>

Spring MVC provides annotations such as `@ExceptionHandler` and `@ControllerAdvice`  
to help manage application-level exceptions in a clean and effective way.

This document explains how to use each annotation and provides learning tests to practice with them.

<br>

# 2. @ExceptionHandler
<br>

The `@ExceptionHandler` annotation is used to define a method for handling exceptions  
that may occur within a specific controller.

A method annotated with `@ExceptionHandler` catches unhandled exceptions  
and allows you to define custom handling logic for them.

The method can receive the exception object as a parameter and return an appropriate response.

<br>

```kotlin
@RestController
public class MyController {
  @ExceptionHandler(FileSystemException::class, RemoteException::class)
    fun handleIoException(ex: IOException): ResponseEntity<String> {
    return ResponseEntity.internalServerError().body(ex.message)
  }
}
```

<br>

### Learning Test
- Test Method: `cholog.ExceptionTest.handleExceptionUsingExceptionHandler`
- Task
  - Add exception handling logic to `cholog.controller.ProductController` to pass the learning test.

<br>

### Reference
- [Spring - Exceptions](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-exceptionhandler.html)

<br>

# 3. @ControllerAdvice

<br>

The `@ControllerAdvice` annotation is applied to classes that handle exceptions globally across the entire application.  
It allows you to centralize exception handling for exceptions that may occur in multiple controllers.

This annotation is typically used in combination with others such as `@ExceptionHandler`.  
You can also configure it to apply only to specific packages or specific controller types.

<br>

```kotlin
@ControllerAdvice
class GlobalExceptionHandler {
  @ExceptionHandler(Exception::class)
  fun handleException(ex: Exception): ResponseEntity<String> {
    return ResponseEntity.internalServerError().body("An error occurred")
  }
}
```

<br>

### Learning Test
- Test Method: `cholog.ExceptionTest.handleExceptionUsingControllerAdvice`
- Task
  - Pass the test by adding exception handling logic in a separate class.
  - Do not modify the existing controllers like `cholog.controller.ProductController`, `cholog.controller.MemberController`

<br>

### Reference
- [Spring - Controller Advice](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-advice.html)
