package cholog

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RestClientTest {
    @Autowired
    private lateinit var todoClient: TodoClientWithRestClient

    @Test
    fun testGetTodos() {
        val todos = todoClient.getTodos()
        Assertions.assertThat(todos).isNotEmpty()
    }

    @Test
    fun testGetTodoWithId() {
        val todo = todoClient.getTodoById(1L)
        Assertions.assertThat(todo?.title).isNotEmpty()
    }

    @Test
    fun testGetTodoWithNonExistentId() {
        val nonExistentId = 9999L

        Assertions.assertThatThrownBy { todoClient.getTodoById(nonExistentId) }
            .isInstanceOf(TodoException.NotFound::class.java)
    }
}
