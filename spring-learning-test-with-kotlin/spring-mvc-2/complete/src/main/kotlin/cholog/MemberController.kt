package cholog

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import java.net.URI
import java.util.concurrent.atomic.AtomicLong

@Controller
class MemberController {
    private val members: MutableList<Member> = ArrayList()
    private val index = AtomicLong(1)

    @PostMapping("/members")
    fun create(
        @RequestBody member: Member,
    ): ResponseEntity<Void> {
        val newMember: Member = Member.toEntity(member, index.getAndIncrement())
        members.add(newMember)
        return ResponseEntity.created(URI.create("/members/" + newMember.id)).build()
    }

    @GetMapping("/members")
    fun read(): ResponseEntity<List<Member>> {
        return ResponseEntity.ok().body(members)
    }

    @PutMapping("/members/{id}")
    fun update(
        @RequestBody newMember: Member,
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        val member =
            members.stream()
                .filter { it: Member -> it.id == id }
                .findFirst()
                .orElseThrow { RuntimeException() }

        member.update(newMember)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/members/{id}")
    fun delete(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        val member =
            members.stream()
                .filter { it: Member -> it.id == id }
                .findFirst()
                .orElseThrow { RuntimeException() }

        members.remove(member)

        return ResponseEntity.noContent().build()
    }
}
