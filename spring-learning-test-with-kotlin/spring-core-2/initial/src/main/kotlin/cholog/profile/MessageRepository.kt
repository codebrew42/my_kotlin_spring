package cholog.profile

interface MessageRepository {
    fun findMessages(): List<String>
}
