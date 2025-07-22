package cholog

import org.springframework.web.client.RestClient

class TodoClientWithRestClient(
    private val restClient: RestClient,
) {
    fun getTodos(): List<Todo> {
        // TODO: Make a GET Request to the TODO API using RestClient
        // TODO: Convert the response to a List<Todo> and return it
        return emptyList()
    }

    fun getTodoById(id: Long): Todo? {
        // TODO: Make a GET Request to the TODO API using RestClient
        // TODO: Convert the response to a Todo object and return it
        return null
    }
}
