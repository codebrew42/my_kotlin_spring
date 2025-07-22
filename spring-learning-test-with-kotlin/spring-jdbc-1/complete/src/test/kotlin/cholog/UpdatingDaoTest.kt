package cholog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate

@JdbcTest
class UpdatingDaoTest {
    private lateinit var updatingDAO: UpdatingDAO
    private lateinit var queryingDAO: QueryingDAO

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    fun setUp() {
        queryingDAO = QueryingDAO(jdbcTemplate)
        updatingDAO = UpdatingDAO(jdbcTemplate)

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS")
        jdbcTemplate.execute(
            "CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))",
        )

        val splitUpNames: List<Array<String>> =
            listOf("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
                .map { name -> name.split(" ").toTypedArray() }
                .toList()
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames)
    }

    @Test
    fun insert() {
        val customer = Customer(firstName = "Leonor", lastName = "Watling")
        updatingDAO.insert(customer)

        val customers = queryingDAO.findCustomerByFirstName("Leonor")

        assertThat(customers).hasSize(1)
    }

    @Test
    fun delete() {
        val rowNum = updatingDAO.delete(1L)

        assertThat(rowNum).isEqualTo(1)
    }

    @Test
    fun keyHolder() {
        val customer = Customer(firstName = "Leonor", lastName = "Watling")
        val id = updatingDAO.insertWithKeyHolder(customer)

        assertThat(id).isNotNull()
    }
}
