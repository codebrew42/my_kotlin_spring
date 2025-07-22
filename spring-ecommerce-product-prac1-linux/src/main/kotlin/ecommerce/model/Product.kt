package ecommerce.model

data class Product(
    var id: Long? = null,
    var name: String? = null,
    var price: Double? = null,
    var imageUrl: String? = null,
)
