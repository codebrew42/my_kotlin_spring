package cholog

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class QueryingDAO(private val jdbcTemplate: JdbcTemplate) {
    // TODO: Learn about RowMapper and apply it in your code.
//    private val customerRowMapper = RowMapper<Customer> { rs: ResultSet, _ ->
//        Customer(
//            rs.getLong("id"),
//            rs.getString("first_name"),
//            rs.getString("last_name")
//        )
//    }

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType)
     */
    fun count(): Int {
        // TODO: Implement a function to count the number of rows in the "customers" table.
        return 0
    }

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType, @Nullable Object... args)
     */
    fun getLastName(id: Long): String? {
        // TODO: Return the last name of the customer with the given ID.
        return null
    }

    /**
     * public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    fun findCustomerById(id: Long): Customer? {
        // TODO: Return a Customer object for the given ID.
        return null
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper)
     */
    fun findAllCustomers(): List<Customer> {
        // TODO: Return all customers as a list.
        return emptyList()
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    fun findCustomerByFirstName(firstName: String): List<Customer> {
        // TODO: Return a list of customers filtered by first name.
        return emptyList()
    }
}
