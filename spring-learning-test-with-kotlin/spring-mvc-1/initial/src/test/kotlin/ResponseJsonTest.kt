package cholog

import io.restassured.RestAssured
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ResponseJsonTest {
    @Test
    fun responseJson() {
        val response =
            RestAssured
                .given().log().all()
                .`when`().get("/json")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.`as`(Person::class.java).name).isEqualTo("brown")
        assertThat(response.`as`(Person::class.java).age).isEqualTo(20)
    }
}
