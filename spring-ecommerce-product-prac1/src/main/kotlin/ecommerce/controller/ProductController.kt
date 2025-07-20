package ecommerce.controller

import ecommerce.model.Product
import ecommerce.model.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/* Points
1. why using RestController? to send JSON file?
2. RestController vs. Controller
1. when to requestParam
2. when to requestBody
*/

/*
 Q1.@GetMapping() uses default endpoint in RequestMapping("endpoint")? YES


 Q2.why no param necessary when reading? maybe because URL is enough? YES (typical)

    The URL path identifies the resource (/api/products)
    You're requesting the entire collection
    If you needed filtering,use query parameters like @RequestParam

Q3. why creating getter-like function in anther class like "productRepository" is it a rule?
    It's not a strict rule, but it follows the Repository Pattern and Separation of Concerns:
        Controller handles HTTP requests/responses
        Repository handles data access logic
        This makes code more maintainable and testable

*/
@RestController
@RequestMapping("/api/products")
class ProductController {
    val productsRepository = ProductRepository()

    @GetMapping()
    fun readAllProducts(): ResponseEntity<List<Product>> {
        val products = productsRepository.read()
        return ResponseEntity.ok(products)
    }
}





























/*

@RestController
@RequestMapping("api/products")
class ProductController {
    @GetMapping()
    fun readAllProducts(): ResponseEntity<List<Product>> {
        //val products = productRepository.getAll()
        return ResponseEntity.ok(products)
    }

    @GetMapping("{id}")
    fun readProductById(): ResponseEntity<Product> {
        val product = productRepository.getById(id)
        return ResponseEntity.ok(product)
    }

    @RestMapping("search")
    fun
}

*/
