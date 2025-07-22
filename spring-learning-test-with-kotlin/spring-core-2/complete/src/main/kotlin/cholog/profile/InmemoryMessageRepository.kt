package cholog.profile

class InmemoryMessageRepository : MessageRepository {
    override fun findMessages(): List<String> {
        return mutableListOf("Development Profile")
    }
}
