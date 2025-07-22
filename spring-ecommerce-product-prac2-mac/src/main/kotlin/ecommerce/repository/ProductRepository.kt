package ecommerce.repository

import ecommerce.exception.NotFoundException
import ecommerce.model.Product
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class ProductRepository(private val jdbc: JdbcTemplate) {
    private val productRowMapper =
        RowMapper<Product> { rs: ResultSet, _ ->
            Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("imageUrl"),
            )
        }

    fun findAll(): List<Product> {
        val sql = "SELECT * FROM TBL_PRODUCTS"
        return jdbc.query(sql, productRowMapper)
    }

    fun findById(id: Long): Product? {
        val sql = "SELECT * from TBL_PRODUCTS where ID = $id"
        return try {
            jdbc.queryForObject(sql, productRowMapper)
        } catch (_: org.springframework.dao.EmptyResultDataAccessException) {
            throw NotFoundException("Product with Id: $id. Not found.")
        }
    }

    fun save(product: Product): Product {
        val sql = "insert into TBL_PRODUCTS (name, price, imageUrl) values (?, ?, ?)"

        val keyHolder: KeyHolder = GeneratedKeyHolder()
        jdbc.update({
            it.prepareStatement(sql, arrayOf("id")).apply {
                setString(1, product.name)
                setDouble(2, product.price!!)
                setString(3, product.imageUrl)
            }
        }, keyHolder)

        return product.copy(id = keyHolder.key!!.toLong())
    }

    fun update(
        id: Long,
        product: Product,
    ): Product {
        val sql = "UPDATE TBL_PRODUCTS SET name = ?, price = ?, imageUrl = ? WHERE id = ?"
        jdbc.update(sql, product.name, product.price, product.imageUrl, id)
        return product.copy(id = id)
    }

    fun delete(id: Long) {
        val sql = "DELETE FROM TBL_PRODUCTS WHERE ID = ?"
        jdbc.update(sql, id)
    }

    fun deleteAll() {
        val sql = "DELETE FROM TBL_PRODUCTS"
        jdbc.update(sql)
    }

    fun patch(
        id: Long,
        product: Product,
    ): Product {
        val existing = findById(id) ?: throw NotFoundException("Product with Id: $id. Not found.")

        val updatedName = product.name ?: existing.name
        val updatedPrice = product.price ?: existing.price
        val updatedImageUrl = product.imageUrl ?: existing.imageUrl

        val sql = "UPDATE TBL_PRODUCTS SET name = ?, price = ?, imageUrl = ? WHERE id = ?"
        jdbc.update(sql, updatedName, updatedPrice, updatedImageUrl, id)

        return Product(id, updatedName, updatedPrice, updatedImageUrl)
    }
}
