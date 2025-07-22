package cholog

import cholog.auth.dto.MemberResponse
import cholog.auth.dto.TokenRequest
import cholog.auth.dto.TokenResponse
import io.restassured.RestAssured
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import javax.swing.plaf.synth.Region.PASSWORD_FIELD

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthTest {
    @LocalServerPort
    var port: Int = 0

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @Test
    fun basicLogin() {
        val member =
            RestAssured
                .given().log().all()
                .auth().preemptive().basic(EMAIL, PASSWORD)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .`when`().get("/members/me/basic")
                .then().log().all()
                .statusCode(HttpStatus.OK.value()).extract().`as`(MemberResponse::class.java)

        assertThat(member.email).isEqualTo(EMAIL)
    }

    @Test
    fun sessionLogin() {
        val cookie =
            RestAssured
                .given().log().all()
                .param(USERNAME_FIELD, EMAIL)
                .param(PASSWORD_FIELD, PASSWORD)
                .`when`().post("/login/session")
                .then().log().all().extract().header("Set-Cookie").split(";")[0]

        val member =
            RestAssured
                .given().log().all()
                .header("Cookie", cookie)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .`when`().get("/members/me/session")
                .then().log().all()
                .statusCode(HttpStatus.OK.value()).extract().`as`(MemberResponse::class.java)

        assertThat(member.email).isEqualTo(EMAIL)
    }

    @Test
    fun tokenLogin() {
        val accessToken =
            RestAssured
                .given().log().all()
                .body(TokenRequest(EMAIL, PASSWORD))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .`when`().post("/login/token")
                .then().log().all().extract().`as`(TokenResponse::class.java).accessToken

        val member =
            RestAssured
                .given().log().all()
                .header("Authorization", "Bearer $accessToken")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .`when`().get("/members/me/token")
                .then().log().all()
                .statusCode(HttpStatus.OK.value()).extract().`as`(MemberResponse::class.java)

        assertThat(member.email).isEqualTo(EMAIL)
    }

    companion object {
        private const val USERNAME_FIELD = "email"
        private const val PASSWORD_FIELD = "password"
        private const val EMAIL = "email@email.com"
        private const val PASSWORD = "1234"
    }
}
