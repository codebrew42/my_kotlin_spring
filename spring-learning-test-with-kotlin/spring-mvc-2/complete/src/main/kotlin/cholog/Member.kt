package cholog

class Member(var id: Long? = null, var name: String, var age: Int) {
    fun update(newMember: Member) {
        this.name = newMember.name
        this.age = newMember.age
    }

    companion object {
        fun toEntity(
            member: Member,
            id: Long,
        ): Member {
            return Member(id, member.name, member.age)
        }
    }
}
