package cholog

import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CRUDTest {
    @Test
    fun create() {
        val response =
            RestAssured
                .given().log().all()
                .body(Member(name = "brown", age = 20))
                .contentType(ContentType.JSON)
                .`when`().post("/members")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
    }

    @Test
    fun read() {
        create()

        val response =
            RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .`when`().get("/members")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.jsonPath().getList("", Member::class.java)).hasSize(1)
    }

    @Test
    fun update() {
        create()

        val response =
            RestAssured
                .given().log().all()
                .body(Member(name = "brown", age = 30))
                .contentType(ContentType.JSON)
                .`when`().put("/members/1")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
    }

    @Test
    fun delete() {
        create()

        val response =
            RestAssured
                .given().log().all()
                .`when`().delete("/members/1")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value())
    }
}
