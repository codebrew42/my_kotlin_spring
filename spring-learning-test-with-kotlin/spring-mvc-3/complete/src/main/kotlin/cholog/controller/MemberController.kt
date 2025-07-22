package cholog.controller

import cholog.exception.NotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController {
    @GetMapping("/members/{id}")
    fun getMember(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        throw NotFoundException("Member not found: id=$id")

        return ResponseEntity.ok().build()
    }
}
