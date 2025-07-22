package ecommerce.controller

import ecommerce.model.Product
import ecommerce.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class ProductController(private val productRepository: ProductRepository) {
    @GetMapping(PRODUCT_PATH)
    fun getProducts(): List<Product> = productRepository.findAll()

    @GetMapping(PRODUCT_PATH_ID)
    fun getProductById(
        @PathVariable id: Long,
    ): ResponseEntity<Product> = ResponseEntity.ok(productRepository.findById(id))

    @PostMapping(PRODUCT_PATH)
    fun createProduct(
        @RequestBody product: Product,
    ): ResponseEntity<Product> {
        val saved = productRepository.save(product)
        return ResponseEntity.created(URI.create("$PRODUCT_PATH/${saved.id}")).body(saved)
    }

    @PutMapping(PRODUCT_PATH_ID)
    fun updateProductById(
        @RequestBody product: Product,
        @PathVariable id: Long,
    ): ResponseEntity<Product> = ResponseEntity.ok(productRepository.update(id, product))

    @PatchMapping(PRODUCT_PATH_ID)
    fun patchProductById(
        @RequestBody product: Product,
        @PathVariable id: Long,
    ): ResponseEntity<Product> = ResponseEntity.ok(productRepository.patch(id, product))

    @DeleteMapping(PRODUCT_PATH_ID)
    fun deleteProductById(
        @PathVariable id: Long,
    ): ResponseEntity<Unit> {
        productRepository.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping(PRODUCT_PATH)
    fun deleteAllProducts(): ResponseEntity<String> {
        productRepository.deleteAll()
        return ResponseEntity.noContent().build()
    }

    companion object {
        const val PRODUCT_PATH = "/api/products"
        const val PRODUCT_PATH_ID = "$PRODUCT_PATH/{id}"
    }
}
