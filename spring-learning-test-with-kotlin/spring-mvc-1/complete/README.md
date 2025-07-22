# 0. Introduction

<br>

This is a learning test for understanding responses in Spring MVC.  
You can find the source code for this test at:  
➡️ [cho-log/spring-learning-test-with-kotlin](https://github.com/cho-log/spring-learning-test-with-kotlin/tree/main/spring-mvc-1)

<br>

# 1. Welcome Page

<br>

Spring Boot supports both static welcome pages and template-based landing pages.  
It first searches for an `index.html` in the pre-configured static content location.  
If not found, it then searches for an `index.html` in the templates directory.  
If either exists, it will be automatically used as the application's start page.

<br> 

### File Path
```
resources
ㄴ static 
  ㄴ index.html
  
or
  
resources
ㄴ templates
  ㄴ index.html
```

<br>

### Learning Test
This test practices setting up a welcome page.

- Test Method: `cholog.ResponseStaticTest.responseIndexPage`
- Task:
  - Use `resources/static/hi.html` to pass the test.
  - Move or rename the file to the appropriate location to enable the welcome page.

<br>

### Reference
- [Spring Boot - Welcome Page](https://docs.spring.io/spring-boot/reference/web/servlet.html#web.servlet.spring-mvc.welcome-page)

<br>

# 2 Static Page

<br>

Files placed under `resources/static` are accessible directly via the browser.  
You can store static assets required by your service in this directory and serve them as needed.

<br>

### Learning Test

This test practices configuring static pages.

- Test Method: `cholog.ResponseStaticTest.responseStaticPage`
- Task
  - Use `resources/templates/static.html` to pass the test.
  - Move or rename the file to the appropriate location for it to be served as a static page.

<br>

### Reference

- [Spring - Static Resources](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/static-resources.html#page-title)

<br>

# 3. Template Engine

<br>

To serve dynamic web pages, a template engine can be used.  
In this test, you will use **Thymeleaf** to handle dynamic responses.

The value for `name` is passed through a query string (e.g., `?name=brown`) and injected into the controller method using `@RequestParam`.  
To pass this value to the view, use a `Model` object.  
The `Model` can be declared as a parameter of the controller method, and values can be added via `addAttribute`.

<br>

### Learning Test

- Test Method: `cholog.ResponseTemplatesTest.responseTemplatesPage`
- Task
  - Implement the method `cholog.MemberController.world` to pass the test.
  - Configure `/hello` to return the `resources/templates/greeting.html` page.

<br>

### Reference

- [Spring - Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Spring - @RequestParam](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestparam.html)
- [Spring - Method Arguments > Model](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html)
- [Baeldung - Introduction to Using Thymeleaf in Spring](https://www.baeldung.com/thymeleaf-in-spring-mvc)

<br>

# 4. Json Response

<br>

To return the controller method’s return value directly in the HTTP response body, you can use the `@ResponseBody` annotation.

<br>

### Learning Test

- Test Method: `cholog.ResponseJsonTest.responseJson`
- Task:
  - Implement the method `cholog.MemberController.json` to pass the test.
  - When the `/json` endpoint is called, the response should be:
    ```json
    { "name": "brown", "age": 20 }
    ```

<br>

### Reference
- [Spring - @ResponseBody](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/responsebody.html#page-title)
- [Spring - Return Values > Other return values](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/return-types.html)
