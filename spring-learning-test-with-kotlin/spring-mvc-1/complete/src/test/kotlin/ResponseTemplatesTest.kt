package cholog

import io.restassured.RestAssured
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ResponseTemplatesTest {
    @Test
    fun responseTemplatesHelloPage() {
        val response =
            RestAssured
                .given().log().all()
                .`when`().get("/hello?name=Brie")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.asString()).contains("Hello, Brie!")
    }
}
