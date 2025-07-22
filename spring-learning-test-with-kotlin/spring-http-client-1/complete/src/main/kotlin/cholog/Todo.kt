package cholog

data class Todo(
    val id: Long? = null,
    val userId: Long? = null,
    val title: String = "",
    val completed: Boolean = false,
)
