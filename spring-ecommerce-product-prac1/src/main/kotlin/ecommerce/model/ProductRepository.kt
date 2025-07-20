package ecommerce.model

import ecommerce.model.Product

class ProductRepository {
    fun read(): List<Product>{
        return Product().testProducts
    }
}