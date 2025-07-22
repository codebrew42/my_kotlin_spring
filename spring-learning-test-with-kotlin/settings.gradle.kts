rootProject.name = "spring-learning-test"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

include("spring-mvc-1:initial", "spring-mvc-1:complete")
include("spring-mvc-2:initial", "spring-mvc-2:complete")
include("spring-mvc-3:initial", "spring-mvc-3:complete")
include("spring-mvc-4:initial", "spring-mvc-4:complete")
include("spring-jdbc-1:initial", "spring-jdbc-1:complete")
include("spring-core-1:initial", "spring-core-1:complete")
include("spring-core-2:initial", "spring-core-2:complete")
include("spring-auth-1:initial", "spring-auth-1:complete")
include("spring-http-client-1:initial", "spring-http-client-1:complete")
