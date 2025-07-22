package cholog

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class QueryingDAO(private val jdbcTemplate: JdbcTemplate) {
    private val customerRowMapper =
        RowMapper<Customer> { rs: ResultSet, _ ->
            Customer(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
            )
        }

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType)
     */
    fun count(): Int {
        val sql = "select count(*) from customers"
        return jdbcTemplate.queryForObject(sql, Int::class.java) ?: 0
    }

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType, @Nullable Object... args)
     */
    fun getLastName(id: Long): String? {
        val sql = "select last_name from customers where id = ?"
        return jdbcTemplate.queryForObject(sql, String::class.java, id)
    }

    /**
     * public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    fun findCustomerById(id: Long): Customer? {
        val sql = "select id, first_name, last_name from customers where id = ?"
        return jdbcTemplate.queryForObject(sql, customerRowMapper, id)
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper)
     */
    fun findAllCustomers(): List<Customer> {
        val sql = "select id, first_name, last_name from customers"
        return jdbcTemplate.query(sql, customerRowMapper)
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    fun findCustomerByFirstName(firstName: String): List<Customer> {
        val sql = "select id, first_name, last_name from customers where first_name = ?"
        return jdbcTemplate.query(sql, customerRowMapper, firstName)
    }
}
