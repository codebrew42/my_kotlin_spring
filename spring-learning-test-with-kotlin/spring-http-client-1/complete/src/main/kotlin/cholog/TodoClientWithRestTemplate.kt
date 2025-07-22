package cholog

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

class TodoClientWithRestTemplate(
    private val restTemplate: RestTemplate,
) {
    fun getTodoById(id: Long): Todo? {
        return try {
            val response: ResponseEntity<Todo> =
                restTemplate.getForEntity(
                    "http://jsonplaceholder.typicode.com/todos/{id}",
                    Todo::class.java,
                    id,
                )
            response.body
        } catch (e: HttpClientErrorException) {
            if (e.statusCode == HttpStatus.NOT_FOUND) {
                throw TodoException.NotFound(id)
            }
            throw TodoException("Failed to get todo with id: $id")
        }
    }
}
