package ecommerce.controller

import ecommerce.model.Product
import ecommerce.model.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
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
        return try {
            val products = productsRepository.readAll()
            ResponseEntity.ok(products)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    //    @ResponseBody //when i needs non-JSON return type (@Controller + @ResponseBody)
    @GetMapping("/{existingId}")
    fun readProductById(
        @PathVariable("existingId") targetId: Long,
    ): ResponseEntity<Product> {
        return try {
            val product = productsRepository.readById(targetId)
            ResponseEntity.ok(product)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping()
    fun addProduct(
        @RequestBody product: Product,
    ): ResponseEntity<Product> {
        return try {
            val createdProduct = productsRepository.addSingleProduct(product)
            ResponseEntity.status(HttpStatus.CREATED).body(createdProduct)
        } catch (e: IllegalStateException) {
            ResponseEntity.unprocessableEntity().build()
        }
    }

    @PutMapping("/{targetId}")
    fun updateFullProductInfo(
        @RequestBody newProductInfo: Product,
        @PathVariable("targetId") id: Long,
    ): ResponseEntity<Product> {
        return try {
            val targetProduct = productsRepository.updateFull(id, newProductInfo)
            ResponseEntity.status(HttpStatus.OK).body(targetProduct)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun updatePartialProductInfo(
        @PathVariable("id") targetId: Long,
        @RequestBody product: Product,
    ): ResponseEntity<Product> {
        return try {
            val updatedProduct = productsRepository.updatePartial(targetId, product)
            ResponseEntity.ok(updatedProduct)
        } catch (e: IllegalStateException) {
            ResponseEntity.notFound().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.unprocessableEntity().build()
        }
    }

    @DeleteMapping()
    fun deleteAllProducts(): ResponseEntity<Void> {
        return try {
            productsRepository.deleteAll()
            ResponseEntity.noContent().build()
        } catch (e: Exception) {
            ResponseEntity.unprocessableEntity().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProductById(
        @PathVariable("id") targetId: Long,
    ): ResponseEntity<Void> {
        return try {
            productsRepository.deleteSingle(targetId)
            ResponseEntity.noContent().build()
        } catch (e: Exception) {
            ResponseEntity.unprocessableEntity().build()
        }
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
