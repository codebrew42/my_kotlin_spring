package ecommerce.model

import ecommerce.model.Product

class ProductRepository {
    fun readAll(): List<Product>{
        if (testProducts.isNotEmpty())
            return testProducts
        throw NoSuchElementException("Products not found")
    }

    fun readById(targetId: Long): Product? {
        return testProducts.find { it.id == targetId }
            ?: throw NoSuchElementException("Product with id $targetId not found")
    }

    fun addSingleProduct(product: Product): Product? {
        //TODO: Understand, improve this
        // Always generate new ID for creation (ignore any existing ID)                                                                                                  │ │
         product.id = testProducts.maxOfOrNull { it.id ?: 0L }?.plus(1L) ?: 1L
        if (testProducts.add(product))
            return testProducts.last()
        throw IllegalStateException("Failed to add product")
    }

    fun updateFull(targetId: Long, newProductInfo: Product): Product? {
        val targetProduct = readAll().find { it.id == targetId }
            ?: throw  NoSuchElementException("Product with id $targetId not found")
        targetProduct.name = newProductInfo.name
        targetProduct.price = newProductInfo.price
        targetProduct.imageUrl = newProductInfo.imageUrl
        return targetProduct
    }

    fun updatePartial(targetId: Long, product: Product): Product{
        val targetProduct = testProducts.find { it.id == targetId }
            ?: throw IllegalStateException("Product with id $targetId does not exist")
        if (product.name.isNullOrBlank() &&
            product.price == null &&
            product.imageUrl.isNullOrBlank()
            ) {
            throw IllegalArgumentException("Product with id $targetId has not valid info")
        }

        targetProduct.apply {
            product.name?.takeIf { it.isNotBlank() }?.let { name = it }
            product.price?.takeIf { it > 0 }?.let { price = it }
            product.imageUrl?.takeIf { it.isNotBlank()}?.let { imageUrl = it }
        }
        return targetProduct
    }

    fun deleteAll(): Boolean {
        return  testProducts.removeAll{ true }
    }

    fun deleteSingle(targetId: Long): Boolean{
        return testProducts.removeIf {  it.id == targetId  }
    }

    companion object {
        var testProducts = mutableListOf<Product>(
            Product(12390123, "Latte", 4.45, "https://google.com"),
            Product(1239323, "Americano", 3.95, "https://google.com"),
            Product(1234, "Icecream", 6.5, "https://google.com")
        )
    }






























}