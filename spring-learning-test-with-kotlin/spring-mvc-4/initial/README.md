# 1. MVC Configuration

<br>

Spring provides the `WebMvcConfigurer` interface to allow application developers to easily customize MVC settings.
You can check the methods of `WebMvcConfigurer` to see what items can be configured.

This document covers view controller mapping, interceptor addition, and `ArgumentResolver` addition.

<br>

### Reference

- [Spring - MVC Config](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config.html)
- [Be careful when using @Configuration classes with @EnableWebMvc in Spring Boot](https://dev.to/xterm/be-careful-when-using-configuration-classes-with-enablewebmvc-in-spring-boot-2n32)

<br>

# 2. View Controller

<br>

To set up a view response for a specific request, you can use the `addViewControllers` method provided by `WebMvcConfigurer`. 
This allows you to respond with a view without writing a controller.

<br>

### Learning Test
- Test Method: `cholog.WebMvcConfigurationTest.addViewControllers`
- Task
  - Implement `cholog.config.WebMvcConfiguration.addViewControllers` to pass the test.

<br>

### Reference
- [Spring - View Controller](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/view-controller.html)

<br>

### Think About It
- What is better, using `WebMvcConfigurer` or writing a controller directly? Why do you think so?

<br>

# 3. Interceptor

<br>

Interceptor can be used to perform tasks such as logging, authentication, and authorization checks.
You can execute specific logic before or after the request reaches the controller using the `HandlerInterceptor` interface.

You can set up an interceptor to operate on specific patterns using the `addInterceptor` method provided by `WebMvcConfigurer`.

<br>

### Learning Test
- Test Method: `cholog.WebMvcConfigurationTest.addInterceptors`
- Task
  - Implement `cholog.config.WebMvcConfiguration.addInterceptors` to pass the test.
    - If someone who is not a member requests the member list, a response indicating that they do not have permission should be returned.
    - Use `CheckLoginInterceptor` to ensure that only requests adhering to the request protocol are responded to.
  - You can check the order of execution by setting breakpoints in `CheckLoginInterceptor` and `MemberController` and debugging.

<br>

### Reference
- [Spring - DispatcherServlet > Interception](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-servlet/handlermapping-interceptor.html)
- [Spring - MVC Config > Interceptors](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/interceptors.html)

<br>

### Think About It
- Why does the Spring framework provide the `HandlerInterceptor` interface and support such behavior?
- What situations can Interceptors be used in?

<br>

# 4. Argument Resolver

<br>

In the Spring Framework, `HandlerMethodArgumentResolver` is a strategy interface used to convert request data into method parameters. 
It allows you to implement logic that creates or manipulates objects to be injected into the parameters of controller methods when they are called.

For example, you can convert a specific header from an HTTP request into an object or retrieve user information from the session to inject into parameters.

You can add custom ArgumentResolvers using the `addArgumentResolvers` method provided by `WebMvcConfigurer`.

<br>

### Learning Test
- Test Method: `cholog.WebMvcConfigurationTest.addArgumentResolvers`
- Task
  - Implement `cholog.config.WebMvcConfiguration.addArgumentResolvers` to pass the test.
    - When a `GET /favorites` request is made, configure the controller to accept `LoginMember` as an argument.

<br>

### Reference
- [Handler Method Argument Resolver](https://www.baeldung.com/spring-mvc-custom-data-binder#1-custom-argument-resolver)

<br>

### Think About It
- Why does the Spring framework provide the `HandlerMethodArgumentResolver` interface and support such behavior?
- What situations can `ArgumentResolvers` be used in?

<br>
