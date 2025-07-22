# 1. Basic Auth

<br>

<img src="src/main/resources/image/basic.png">

Basic Authentication is a simple method for transmitting a user's username and password to a web server.  
The client sends the credentials in the HTTP request header using the format:

```http request
Authorization: Basic <credentials>
```

Here, `<credentials>` is a Base64-encoded string made by concatenating the username and password with a colon (`:`) like this:  
`{{username}}:{{password}}` → then encoded using Base64.

The result is a string that doesn’t expose the credentials directly to the human eye,  
but it’s still easily decodable — so Basic Auth should always be used over HTTPS.

```http request
Headers: Authorization=Basic ZW1haWxAZW1haWwuY29tOjEyMzQ=
```

<br>

To apply Basic Authentication in tests using **RestAssured**,  
you can configure the request to automatically include the `Authorization` header like this:

```kotlin
.auth().preemptive().basic(EMAIL, PASSWORD)
```

<br>

To extract and inspect the information in the Authorization header, you can use the following code:

```kotlin
val authInfo = authorizationExtractor.extract(request)
```

<br>

### Learning Test
- Test Method: `cholog.AuthTest.basicLogin`
- Task
    - Implement `cholog.BasicLoginController` to pass the test.
    - Learn difference between `.auth().preemptive().basic()` and `.auth().basic()`.

<br>

### Reference
- [`@RequestParam` or `HttpServletRequest`](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments)

<br>

# 2. Session Login

<br>

<img src="src/main/resources/image/session.png">

With **Basic Authentication**, the user's ID and password are sent in every request,  
which can be a security risk and is generally inefficient.

In contrast, **session-based authentication** sends credentials **only once** during the initial login.  
After verifying the credentials, the server generates a **session ID** and returns it in the response.

The client then includes this session ID in all subsequent requests to maintain the authenticated state.  
This reduces the need to repeatedly prove the user's identity with each request.

<br>

The session ID is sent in the response using the `Set-Cookie` header.

```http request
HTTP/1.1 200
Set-Cookie: JSESSIONID=89EA9B9B9F00EAC2B8D2208649EA6260; Path=/; HttpOnly
```

<br>

On subsequent requests, the client includes the session ID in the `Cookie` header like this:

```http request
Headers: Cookie=JSESSIONID=89EA9B9B9F00EAC2B8D2208649EA6260
```

<br>

### Learning Test
- Test Method: `cholog.AuthTest.sessionLogin`
- Task
    - Implement `cholog.SessionLoginController` to pass the test.

<br>

### Reference
- [HttpSession](https://www.baeldung.com/spring-security-session#2-injecting-the-raw-session-into-a-controller)

# 3. Token Login

<br>

<img src="src/main/resources/image/token.png">

In **token-based authentication**, the user sends their credentials (such as username and password) once during login.  
The server verifies the credentials and responds with an **encrypted access token** (e.g., a JWT).

The client then includes this token in all subsequent requests to authenticate itself.  
For each request, the server verifies the token and determines the user's identity and access permissions.

<br>

In **token-based authentication**, the user sends their credentials (such as username and password) once during login.  
The server verifies the credentials and responds with an **encrypted access token** (e.g., a JWT).

The client then includes this token in all subsequent requests to authenticate itself.  
For each request, the server verifies the token and determines the user's identity and access permissions.

---

### Sending the Access Token

After the token is issued, the client includes it in every request by attaching it to the `Authorization` header:

```http request
Headers: Authorization=Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBlbWFpbC5jb20iLCJpYXQiOjE3MDU4NDU2NzIsImV4cCI6MTcwNTg0OTI3Mn0.YibLSi-PenIMc0LJUW50A_hq98uZmQu7OAdIxIvF4MY
```

<br>

### Learning Test
- Test Method: `cholog.AuthTest.tokenLogin`
- Task
    - Implement `cholog.TokenLoginController` to pass the test.
