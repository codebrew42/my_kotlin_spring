# spring-ecommerce-product

## features and requirements

### practice

- [] create one GetMapping, make it work


### step1

#### features
- [] build a simple HTTP API 
  - [] user retrieve products
  - [] user add products
  - [] user update products
  - [] user delete products
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
- [] create class 'Product'
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
