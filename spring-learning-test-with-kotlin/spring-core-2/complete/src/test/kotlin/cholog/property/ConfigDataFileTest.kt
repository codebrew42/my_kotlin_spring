package cholog.property

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ConfigDataFileTest {
    @Autowired
    private lateinit var jwtTokenKeyProvider: JwtTokenKeyProvider

    @Test
    fun getPropertyValueWithSpringBoot() {
        Assertions.assertThat(jwtTokenKeyProvider.secretKey)
            .isEqualTo(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                    "eyJzdWIiOiIiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9." +
                    "ih1aovtQShabQ7l0cINw4k1fagApg3qLWiB8Kt59Lno",
            )
    }
}
