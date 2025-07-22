package cholog

import org.springframework.web.client.RestTemplate

class TodoClientWithRestTemplate(
    private val restTemplate: RestTemplate,
) {
    fun getTodoById(id: Long): Todo? {
        // TODO: Make a GET Request to the TODO API using RestTemplate
        // TODO: Convert the response to a Todo object and return it
        // TODO: If the ID does not exist, throw TodoException.NotFound
        return null
    }
}
