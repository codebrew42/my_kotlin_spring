# 1. HTTP Clients in Spring Framework

<br>

Spring Framework provides various HTTP clients to enable applications to communicate with external APIs.
The HTTP clients provided by Spring include `RestClient`, `WebClient`, and `RestTemplate`.

This document covers `RestTemplate` and the `RestClient` introduced in Spring 6.1.

<br>

# 2. RestTemplate

## 2-1. GET Request

<br>

You can use the `getForEntity()` method of `RestTemplate` to make a GET request and convert the response into a desired POJO.

<br>

### Learning Test
- Test Method: `cholog.RestTemplateTest.testGetTodoWithId`
- Task
  - Implement `cholog.TodoClientWithRestTemplate.getTodoById` to pass the test.
    - Using `TodoClient`, retrieve a Todo with a specific id.
    - Todo retrieval URL: http://jsonplaceholder.typicode.com/todos/1

<br>

### Reference
- [The Guide to RestTemplate](https://www.baeldung.com/rest-template)

<br>

## 2-2. Handling Exceptions

<br>

While using `RestTemplate` to communicate with external APIs, you can handle exceptions based on the response code.

<br>

### Learning Test
- Test Method: `cholog.RestTemplateTest.testGetTodoWithNonExistentId`
- Task
  - Implement `cholog.TodoClientWithRestTemplate.getTodoById` to pass the test.
  - When a request is made for a non-existent id, the `TodoException.NotFound` exception should be thrown.

<br>

### Reference
- [Spring RestTemplate Error Handling](https://www.baeldung.com/spring-rest-template-error-handling)

<br>


# 3. RestClient

## 3-1. GET Request

<br>

You can use the `get()` method of `RestClient` to make a GET request.

<br>

### Learning Test
- Test Method: `cholog.RestClientTest.testGetTodos`
- Task
  - Implement `cholog.TodoClientWithRestClient.getTodos` to pass the test.
    - Using `TodoRestClient`, retrieve the list of Todos.
    - Todo list retrieval URL: http://jsonplaceholder.typicode.com/todos
    - The host of the external API is set in `RestClientConfig`.

<br>

### Reference
- [Spring - REST Clients](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html)
- [Spring blog - New in Spring 6.1: RestClient](https://spring.io/blog/2023/07/13/new-in-spring-6-1-restclient)

<br>

## 3-2. Converting GET Request Responses

<br>

You can use the `body()` method of `RestClient` to convert the response into a desired POJO.

<br>

### Learning Test
- Test Method: `cholog.RestClientTest.testGetTodoWithId`
- Task
  - Implement `cholog.TodoClientWithRestClient.getTodoById` to pass the test.
    - Using `TodoRestClient`, retrieve a Todo with a specific id.
    - Todo retrieval URL: http://jsonplaceholder.typicode.com/todos/1

<br>

## 3-3. Handling Exceptions

<br>

You can use the `onStatus()` method of `RestClient` to handle exceptions based on the HTTP response code.

<br>

### Learning Test
- Test Method: `cholog.RestClientTest.testGetTodoWithNonExistentId`
- Task
  - Implement `cholog.TodoClientWithRestClient.getTodoById` to pass the test.
  - When a request is made for a non-existent id, the `TodoException.NotFound` exception should be thrown.

<br>

# 4. Think About It

- This document only covers `GET` requests. Create and run learning tests for other HTTP methods such as `POST`, `PUT`, and `DELETE`.
  - Use the API at https://jsonplaceholder.typicode.com/guide/ to explore other methods.
- Spring provides various HTTP clients, each with different characteristics and usage methods.
  - Write learning tests for clients other than `RestTemplate` and `RestClient` to experience their differences.
  - Explore the features of each client and consider which client is suitable for different situations.
- List potential exception scenarios when communicating with external APIs and think about how to handle each case.
