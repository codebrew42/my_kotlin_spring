package cholog

import org.springframework.web.client.RestClient

class TodoClientWithRestClient(
    private val restClient: RestClient,
) {
    fun getTodos(): List<Todo> {
        val todoBody =
            restClient.get()
                .uri("/todos")
                .retrieve()
                .body(Array<Todo>::class.java)

        return todoBody?.toList() ?: emptyList()
    }

    fun getTodoById(id: Long): Todo? {
        return restClient.get()
            .uri("/todos/{id}", id)
            .retrieve()
            .onStatus({ status -> status.value() == 404 }) { response, errorBody ->
                throw TodoException.NotFound(id)
            }
            .body(Todo::class.java)
    }
}
