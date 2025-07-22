package ecommerce.model

data class Product(
    var id: Long? = null,
    var name: String? = null,
    var price: Double? = null,
    var imageUrl: String? = null,
) {
    fun copyFrom(other: Product): Product =
        this.copy(
            name = other.name,
            price = other.price,
            imageUrl = other.imageUrl,
        )

    fun partialUpdate(other: Product): Product =
        this.copy(
            name = other.name ?: this.name,
            price = other.price ?: this.price,
            imageUrl = other.imageUrl ?: this.imageUrl,
        )

    companion object {
        fun toEntity(
            id: Long,
            product: Product,
        ): Product {
            return Product(id, product.name, product.price, product.imageUrl)
        }
    }
}
