package cholog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate

@JdbcTest
class QueryingDaoTest {
    private lateinit var queryingDAO: QueryingDAO

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    fun setUp() {
        queryingDAO = QueryingDAO(jdbcTemplate)

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
    fun count() {
        val count = queryingDAO.count()

        assertThat(count).isEqualTo(4)
    }

    @Test
    fun lastName() {
        val lastName = queryingDAO.getLastName(1L)

        assertThat(lastName).isEqualTo("Woo")
    }

    @Test
    fun findCustomerById() {
        val customer = queryingDAO.findCustomerById(1L)

        assertThat(customer).isNotNull()
        assertThat(customer?.lastName).isEqualTo("Woo")
    }

    @Test
    fun findAllCustomers() {
        val customers = queryingDAO.findAllCustomers()

        assertThat(customers).hasSize(4)
    }

    @Test
    fun findCustomerByFirstName() {
        val customers = queryingDAO.findCustomerByFirstName("Josh")

        assertThat(customers).hasSize(2)
    }
}
