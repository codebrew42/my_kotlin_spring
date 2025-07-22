# 1. CRUD API

<br>

CRUD represents the basic data manipulation operations of most software systems:  
**Create**, **Read**, **Update**, and **Delete**.  
When building APIs to manage resources, implementing CRUD functionality is standard practice.

<br>

# 2. create
<br>

This is the API used to **create** a new resource.  
Use the `POST` method to send data to the server.  
Provide the necessary fields in the request body as JSON.

<br>

```http request
POST /members HTTP/1.1
content-type: application/json

{
    "name": "Brown",
    "age": 20
}
```

<br>

If the request is successful, the server will respond with a `201 Created` status.
The response will include a `Location` header pointing to the newly created resource,
and optionally include the resource itself in the response body.

<br>

```http request
HTTP/1.1 201 
Location: /members/1
Content-Type: application/json

{
    "id": 1,
    "name": "Brown",
    "age": 20
}
```

<br>

### Learning Test
- Test Method: `cholog.CRUDTest.create`
- Task
  - Implement `cholog.MemberController.create` to pass the test.

<br>

### Reference
- [Spring - ResponseEntity](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/responseentity.html)
- [Spring - @RequestBody](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestbody.html)

<br>

# 3. read

<br>

This is the API used to **retrieve** resources.
Use the `GET` method to request data from the server.

<br>

```http request
GET /members HTTP/1.1
```

<br>

If successful, the server will respond with a `200 OK` status,
and return the list of resources in the response body.

<br>

```http request
HTTP/1.1 200 
Content-Type: application/json

[
    {
        "id": 1,
        "name": "Brown",
        "age": 20
    },
    {
        "id": 2,
        "name": "Brie",
        "age": 10
    }
]
```

<br>

### Learning Test
- Test Method: `cholog.CRUDTest.read`
- Task
  - Implement `cholog.MemberController.read` to pass the test.

<br>

# 4. update

<br>

This is the API used to **update** a resource.
Use the `PUT` method to fully replace an existing resource.
While `PATCH` can be used for partial updates, `PUT` is used here to replace the entire resource.
Include the resource identifier in the URL, and provide the updated data in the request body.

<br>

```http request

PUT /members/1 HTTP/1.1

{
    "name": "Brown",
    "age": 30
}
```

<br>

If the request is successful, the server will respond with a `200 OK` status.

<br>

```http request
HTTP/1.1 200
```

<br>


### Learning Test
- Test Method: `cholog.CRUDTest.update`
- Task
  - Implement `cholog.MemberController.update` to pass the test.

<br>

### Reference
- [Spring - Method Arguments > @PathVariable](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html)

<br>


# 5. delete

<br>

This is the API used to **delete** a resource.
Use the `DELETE` method, and include the identifier of the resource in the URL.

<br>

```http request
DELETE /members/1 HTTP/1.1
```

<br>

If successful, the server will respond with a `204 No Content` status.

<br>

```http request
HTTP/1.1 204
```

<br>

### Learning Test
- Test Method: `cholog.CRUDTest.delete`
- Task
  - Implement `cholog.MemberController.delete` to pass the test.
