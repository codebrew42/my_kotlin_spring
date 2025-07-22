package cholog.profile

class JdbcMessageRepository : MessageRepository {
    override fun findMessages(): List<String> {
        return mutableListOf("Production Profile")
    }
}
