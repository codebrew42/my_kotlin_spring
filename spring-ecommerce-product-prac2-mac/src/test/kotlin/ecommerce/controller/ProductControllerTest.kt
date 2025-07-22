package ecommerce.controller

import ecommerce.model.Product
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductControllerTest {
    @Test
    fun getProducts() {
        val response =
            RestAssured.given().log().all()
                .accept(ContentType.JSON)
                .`when`().get("/api/products")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        val names = response.body().jsonPath().getList<String>("name")
        assertThat(names).isNotEmpty()
        assertThat(names.size).isEqualTo(5)
    }

    @Test
    fun getProduct() {
        val product = Product(name = "Speaker", price = 99.99)
        val id =
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(product)
                .post("/api/products")
                .then().extract().jsonPath().getLong("id")

        val response =
            RestAssured.get("/api/products/$id")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.body().jsonPath().getString("name")).isEqualTo("Speaker")
        assertThat(response.body().jsonPath().getFloat("price")).isEqualTo(99.99f)
    }

    @Test
    fun getProduct_notFound() {
        val response =
            RestAssured.get("/api/products/999999")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value())
    }

    @Test
    fun createProduct() {
        val newProduct = Product(name = "Monitor", price = 150.0, imageUrl = "https://example.com/monitor.jpg")

        val response =
            RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(newProduct)
                .`when`().post("/api/products")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        assertThat(response.body().jsonPath().getString("name")).isEqualTo("Monitor")
        assertThat(response.body().jsonPath().getFloat("price")).isEqualTo(150.0f)
    }

    @Test
    fun updateProduct() {
        val created = Product(name = "Mouse", price = 25.0)
        val id =
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(created)
                .post("/api/products")
                .then().extract().jsonPath().getLong("id")

        val updated = Product(name = "Gaming Mouse", price = 45.0)

        val response =
            RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(updated)
                .put("/api/products/$id")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.body().jsonPath().getString("name")).isEqualTo("Gaming Mouse")
        assertThat(response.body().jsonPath().getFloat("price")).isEqualTo(45.0f)
    }

    @Test
    fun patchProduct() {
        val created = Product(name = "Tablet", price = 299.0)
        val id =
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(created)
                .post("/api/products")
                .then().extract().jsonPath().getLong("id")

        val patch = mapOf("price" to 249.0)

        val response =
            RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(patch)
                .patch("/api/products/$id")
                .then().log().all().extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.body().jsonPath().getFloat("price")).isEqualTo(249.0f)
    }

    @Test
    fun deleteProduct() {
        val created = Product(name = "Keyboard", price = 59.99)
        val id =
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(created)
                .post("/api/products")
                .then().extract().jsonPath().getLong("id")

        val deleteResponse =
            RestAssured.delete("/api/products/$id")
                .then().log().all().extract()

        assertThat(deleteResponse.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value())

        val getResponse =
            RestAssured.get("/api/products/$id")
                .then().log().all().extract()

        assertThat(getResponse.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value())
    }
}
