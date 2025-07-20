package ecommerce.model

data class Product (
    var id: Long = 0,
    var name: String = "",
    var price: Double = 0.0,
    var imageUrl: String = "",
) {
    val testProducts = listOf<Product>(
        Product(12390123, "Latte", 4.45, "https://google.com"),
        Product(1239323, "Americano", 3.95, "https://google.com"),
        Product(1345, "Icecream", 6.5, "https://google.com")
    )
}