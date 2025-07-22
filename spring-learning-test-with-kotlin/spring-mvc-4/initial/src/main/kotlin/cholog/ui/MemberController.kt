package cholog.ui

import cholog.domain.LoginMember
import cholog.dto.FavoriteResponse
import cholog.dto.MemberResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController {
    @GetMapping("/members/me")
    fun findMemberOfMine(
        @AuthenticationPrincipal loginMember: LoginMember?,
    ): ResponseEntity<LoginMember> {
        return ResponseEntity.ok().body(loginMember)
    }

    @GetMapping("/admin/members")
    fun showMembers(): ResponseEntity<List<MemberResponse>> {
        return ResponseEntity.ok().body(emptyList())
    }

    @GetMapping("/favorites")
    fun showFavorites(
        @AuthenticationPrincipal loginMember: LoginMember?,
    ): ResponseEntity<List<FavoriteResponse>> {
        if (loginMember?.id == null) {
            throw AuthorizationException()
        }

        return ResponseEntity.ok().body(emptyList())
    }
}
