package ecommerce.model

data class Product (
    var id: Long = 0,
    var name: String = "",
    var price: Double = 0.0,
    var imageUrl: String = "",
)