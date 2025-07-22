package ecommerce.service

import ecommerce.exception.NotFoundException
import ecommerce.model.Product
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong

@Service
class ProductService {
    private val products = mutableMapOf<Long, Product>()
    private val index = AtomicLong(1)

    init {
        preloadProducts()
    }

    fun findAll(): List<Product> = products.values.toList()

    fun findById(id: Long): Product = products[id] ?: throw NotFoundException("Product with Id: $id. Not found.")

    fun save(product: Product): Product {
        val id = index.getAndIncrement()
        val saved = Product.toEntity(id, product)
        products[id] = saved
        return saved
    }

    fun update(
        id: Long,
        product: Product,
    ): Product {
        val existing = findById(id)
        val updated = existing.copyFrom(product)
        products[id] = updated
        return updated
    }

    fun patch(
        id: Long,
        product: Product,
    ): Product {
        val existing = findById(id) ?: throw NotFoundException("Product with Id: $id. Not found.")
        val updated = existing.partialUpdate(product)
        products[id] = updated
        return updated
    }

    fun delete(id: Long) {
        if (products.remove(id) == null) throw NotFoundException("Product with Id: $id. Not found.")
    }

    fun deleteAll() {
        products.clear()
    }

    private fun preloadProducts() {
        listOf(
            Product(index.getAndIncrement(), "Car", 1000.0, "www.some.com"),
            Product(index.getAndIncrement(), "Bike", 200.0, "www.example.com/bike"),
            Product(index.getAndIncrement(), "Truck", 30000.0, "www.example.com/truck"),
        ).forEach { products[it.id!!] = it }
    }
}
