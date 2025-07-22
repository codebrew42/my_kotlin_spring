package cholog

import io.restassured.RestAssured
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ResponseStaticTest {
    @Test
    fun responseIndexPage() {
        val response =
            RestAssured
                .given().log().all()
                .`when`().get("")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
    }

    @Test
    fun responseStaticPage() {
        val response =
            RestAssured
                .given().log().all()
                .`when`().get("/static.html")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
    }
}
