package cholog

import io.restassured.RestAssured
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebMvcConfigurationTest {
    @LocalServerPort
    var port: Int = 0

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @Test
    fun addViewControllers() {
        // when
        val response =
            RestAssured
                .given().log().all()
                .`when`().get("/")
                .then().log().all().extract()

        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
    }

    @Test
    fun addInterceptors() {
        RestAssured
            .given().log().all()
            .`when`().get("/admin/members")
            .then().log().all()
            .statusCode(HttpStatus.UNAUTHORIZED.value())
            .extract()
    }

    @Test
    fun addArgumentResolvers() {
        RestAssured
            .given().log().all()
            .`when`().get("/favorites")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()
    }
}
