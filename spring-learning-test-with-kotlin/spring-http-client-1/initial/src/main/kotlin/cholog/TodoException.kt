package cholog

open class TodoException(message: String) : RuntimeException(message) {
    class NotFound(id: Long) : TodoException("Todo not found with id: $id")
}
