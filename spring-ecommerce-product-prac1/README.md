# spring-ecommerce-product

## features and requirements

### practice

- [] create one GetMappin[]()g, make it work


### step1

#### features
- [x] build a simple HTTP API 
  - [x] user retrieve products
  - [x] user add products
  - [x] user update products
  - [x] user delete products
- [] API sends and receive HTTP message like this example
```
//REQUEST
GET /api/products HTTP/1.1

//RESPONSES
HTTP/1.1 200
Content-Type: application/json

[
    {
        "id": 8159230,
        "name": "Americano T",
        "price": 4.50,
        "imageUrl": "https:blahblah.jpg"
    }
]

```
#### requirements
- [] HTTP requests and responses are in JSON format
- [] store data in memory using appropriate Kotlin Collection Framework
  - (not using separate database)
##### code-style
- [] indentation depth max 2
- [] each function/method max 15 lines
- [] each function/method does only one thing
---
#### separate-features-into-small-tasks (learning)
- [] build a simple HTTP API
  - [] user retrieve products
  - [] user add products
  - [] user update products
  - [] user delete products
##### steps
- [x] create class 'Product'
- [] create class 'ProductController'
- [] add in-memory storage, instead of database 
- [] test your API
#### thoughts/doubts
- concepts
  - clients send requests to "endpoint"
    - how? idk
    - maybe backends doesn't need to care this?
  - server deal with it and send responses back
    - listen to "endpoint" by ___Mapping("/endpoint")
    - @Controller(form: ?) @RestController(form: JSON)
  - necessary classes
    - class ___Controller
    - class product (raw data)
      - id: Long, name: String, price: double?float?, imageUrl: String


---
#### mistakes

Critical Mistakes:
```
1. Variable Shadowing (Line 68)

fun addProduct(@RequestBody product: Product): ResponseEntity<Product> {
return try {
val product = productsRepository.addSingleProduct(product) // ❌ shadows parameter
You're reusing the same variable name product for both parameter and result.

2. Wrong HTTP Status for PUT Update (Line 81)

ResponseEntity.status(HttpStatus.FOUND).body(targetProduct) // ❌ FOUND is 302 redirect
HttpStatus.FOUND (302) is for redirects, not successful updates. Should use OK (200) or NO_CONTENT (204).

3. Wrong Path Variable Mapping (Line 75)

@PutMapping("{targetId}") // ❌ Missing leading slash
Should be @PutMapping("/{targetId}") - missing the /.

4. Unused Path Variable (Lines 76-77)

fun updateFullProductInfo(@RequestBody newProductInfo: Product,
@PathVariable("targetId") id: Long) // ❌ id parameter unused
You extract id from path but never use it in the function.

5. Wrong Exception Handling for PUT (Lines 82-84)

} catch (e: NoSuchElementException) {
ResponseEntity.unprocessableEntity().build() // ❌ Should be NOT_FOUND (404)
}
When a resource doesn't exist for update, return 404, not 422.
```
Standard HTTP Status Codes for Each Case:

GET Operations:

- Success: 200 OK with data
- Not Found: 404 NOT_FOUND
- Empty Collection: 200 OK with empty array (not 404)

POST (Create):

- Success: 201 CREATED with created resource
- Validation Error: 400 BAD_REQUEST
- Business Logic Error: 422 UNPROCESSABLE_ENTITY

PUT (Update):

- Success: 200 OK with updated resource OR 204 NO_CONTENT
- Not Found: 404 NOT_FOUND
- Validation Error: 400 BAD_REQUEST

DELETE:

- Success: 204 NO_CONTENT
- Not Found: 404 NOT_FOUND

Yes, Always Use ResponseEntity<>

In REST APIs, always use ResponseEntity<T> because:
- It gives you control over HTTP status codes
- It allows custom headers
- It provides consistent API responses
- It's the Spring Boot best practice for REST controllers

The pattern should be:
ResponseEntity.status(HttpStatus.CODE).body(data)
// or shortcuts:
ResponseEntity.ok(data)           // 200
ResponseEntity.created(uri).body(data) // 201  
ResponseEntity.notFound().build() // 404
ResponseEntity.noContent().build() // 204
