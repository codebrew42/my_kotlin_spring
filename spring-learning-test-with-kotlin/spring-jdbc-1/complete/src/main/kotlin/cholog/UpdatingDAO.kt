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
        val sql = "insert into customers (first_name, last_name) values (?, ?)"
        jdbcTemplate.update(sql, customer.firstName, customer.lastName)
    }

    /**
     * public int update(String sql, @Nullable Object... args)
     */
    fun delete(id: Long): Int {
        val sql = "delete from customers where id = ?"
        return jdbcTemplate.update(sql, id)
    }

    /**
     * public int update(final PreparedStatementCreator psc, final KeyHolder generatedKeyHolder)
     */
    fun insertWithKeyHolder(customer: Customer): Long {
        val sql = "insert into customers (first_name, last_name) values (?, ?)"

        val keyHolder: KeyHolder = GeneratedKeyHolder()
        jdbcTemplate.update({
            it.prepareStatement(sql, arrayOf("id")).apply {
                setString(1, customer.firstName)
                setString(2, customer.lastName)
            }
        }, keyHolder)

        return keyHolder.key!!.toLong()
    }
}
