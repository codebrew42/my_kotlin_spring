package cholog

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository

@Repository
class UpdatingDAO(private val jdbcTemplate: JdbcTemplate) {
    /**
     * public int update(String sql, @Nullable Object... args)
     */
    fun insert(customer: Customer) {
        // TODO: Save a customer to the database.
    }

    /**
     * public int update(String sql, @Nullable Object... args)
     */
    fun delete(id: Long): Int {
        // TODO: Delete the customer with the given ID and return the number of affected rows.
        return 0
    }

    /**
     * public int update(final PreparedStatementCreator psc, final KeyHolder generatedKeyHolder)
     */
    fun insertWithKeyHolder(customer: Customer): Long {
        val sql = "insert into customers (first_name, last_name) values (?, ?)"

        val keyHolder: KeyHolder = GeneratedKeyHolder()

        // TODO: Learn about KeyHolder, and return the generated customer ID after saving the customer.

        return keyHolder.key!!.toLong()
    }
}
