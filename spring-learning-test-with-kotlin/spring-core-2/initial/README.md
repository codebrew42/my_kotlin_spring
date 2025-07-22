# 1. Java-based Configuration

<br>

We learn how to define a Spring container using Java code and annotations provided by Spring in [spring-core-1](./../../spring-core-1).
This document discusses how to register Spring beans and manage dependencies using annotations and Kotlin code.

<br>

## 1.1. Declaring a Bean

<br>

You can register Spring beans in Kotlin code using the `@Configuration` annotation and methods annotated with `@Bean`.
The simple `@Configuration` class looks like this:

<br>

```kotlin
class AppConfig {
  @Bean
  fun myService(): MyServiceImpl {
    return MyServiceImpl()
  }
}
```

<br>

### Learning Test
- Test Method: `cholog.configuration.ConfigTest.shouldCreateBean`
- Task
  - Implement `cholog.configuration.config.AppConfig` to pass the test.

<br>

## 1.2 Bean Dependencies

<br>

In Spring, beans can depend on other beans. You can define these dependencies using the `@Bean` methods in a `@Configuration` class.

<br>

```kotlin
class AppConfig {
  @Bean
  fun beanOne(): BeanOne {
    return BeanOne(beanTwo())
  }

  @Bean
  fun beanTwo(): BeanTwo {
    return BeanTwo()
  }
}
```

<br>

You can also define dependencies using the beans defined in other `@Configuration` classes. 
- [Spring - Bean Dependencies](https://docs.spring.io/spring-framework/reference/core/beans/java/bean-annotation.html#beans-java-dependencies)

<br>

### Learning Test
- Test Method: `cholog.configuration.ConfigTest.shouldCreateBeansThatHasDependency`
- Task
  - Implement `cholog.configuration.config.AppConfig` to pass the test.

<br>

# 2. Property

<br>

Property is a key-value pair that stores configuration values for an application. 
For example, settings like database connection information or API keys.

In Spring, the `Environment` interface integrates these property sources and provides functionality to retrieve the required property values.

<br>

## 2.1. Using @PropertySource and Environment

<br>

You can load a properties file using the `@PropertySource` annotation and read property values using the `Environment` interface.

<br>

```kotlin
@Configuration
@PropertySource("classpath:/com/myco/app.properties")
class AppConfig {
  @Autowired
  lateinit var env: Environment

  @Bean
  fun testBean(): TestBean {
    val testBean = TestBean()
    testBean.name = env.getProperty("testbean.name")
    return testBean
  }
}
```

<br>

### Learning Test
- Test Method: `cholog.property.PropertySourceConfigTest.getPropertyValueFromUsingEnvironment`
- Task
  - Implement `cholog.property.config.PropertySourceConfig` to pass the test.

<br>

## 2.2. Using @PropertySource and @Value

<br>

You can also use the `@Value` annotation to inject property values directly into your beans.

<br>

```kotlin
@Configuration
@PropertySource("classpath:/com/myco/app.properties")
class AppConfig {
  @Bean
  fun testBean(@Value("\${testbean.name}") name: String): TestBean {
    val testBean = TestBean()
    testBean.name = name
    return testBean
  }
}
```

<br>

### Learning Test
- Test Method: `cholog.property.PropertySourceConfigTest.getPropertyValueFromUsingAnnotation`
- Task
  - Implement `cholog.property.config.PropertySourceConfig` to pass the test.

<br>

## 2.3. Externalized Configuration (Spring Boot)

<br>

There are various ways to set property values in Spring applications.

Especially when using Spring Boot, you can easily set property values using `application.properties` (or `application.yaml`) files.

<br>

### Learning Test
- Test Method: `cholog.property.ConfigDataFileTest.getPropertyValueWithSpringBoot`
- Task
  - Implement `cholog.property.config.AuthConfig` to pass the test.

<br>

# 3. Profile

<br>

Profile is a logical grouping of application settings.
For example, you can separate configurations suitable for different environments like development, testing, and production.

In Spring, profiles allow you to apply different configurations for the same application based on the environment.

`Environment` interface provided by Spring manages the currently active profiles and the default profile.

<br>

## 3.1. @Profile

<br>

You can use the `@Profile` annotation to register beans based on specific profiles.
`@Profile` can be applied at both the class level and method level.

<br>

### Case for @Configuration Class

When you apply `@Profile` to a `@Configuration` class, all beans defined in that class will only be registered when the specified profile is active.

```kotlin
@Configuration
@Profile("development")
class StandaloneDataConfig {
  @Bean
  fun dataSource(): DataSource {
    return EmbeddedDatabaseBuilder()
      .setType(EmbeddedDatabaseType.HSQL)
      .addScript("classpath:com/bank/config/sql/schema.sql")
      .addScript("classpath:com/bank/config/sql/test-data.sql")
      .build()
  }
}
```

### Case for @Bean Method

When you apply `@Profile` to a `@Bean` method, that specific bean will only be registered when the specified profile is active.

```kotlin
@Configuration
class AppConfig {
  @Bean("dataSource")
  @Profile("development")
  fun standaloneDataSource(): DataSource {
    return EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:com/bank/config/sql/schema.sql")
            .addScript("classpath:com/bank/config/sql/test-data.sql")
            .build()
  }

  @Bean("dataSource")
  @Profile("production")
  @Throws(Exception::class)
  fun jndiDataSource(): DataSource {
    val ctx = InitialContext()
    return ctx.lookup("java:comp/env/jdbc/datasource") as DataSource
  }
}
```

<br>

### Learning Test
- Test Method: 
  - `cholog.profile.ProfileConfigTest.shouldCreateInmemoryRepositoryBean`
  - `cholog.profile.ProfileConfigTest.shouldCreateJdbcRepositoryBean`
- Task
  - Implement `cholog.profile.config.ProfileConfig` to pass the test.

<br>

# 4. Think About It

- There are various ways to define a Spring container. Think about the pros and cons of managing beans using Kotlin code with `@Component` and `@Autowired` annotations compared to using Kotlin code with `@Configuration` and `@Bean`. Consider which approach you would choose in different situations.
- You can also define a Spring container using XML. Learn about how to use XML for defining the Spring container and compare it with using Kotlin code.
- Spring Boot uses the property file convention (`application-{profile}`) to load property files for the active profile. For example, if the active profile is `prod`, it loads `application.properties` and `application-prod.properties`. Think about which values you would manage in the property files using this feature.

<br>

# 5. Reference

- [Spring - @Configuration](https://docs.spring.io/spring-framework/reference/core/beans/java/configuration-annotation.html)
- [Spring - @Bean](https://docs.spring.io/spring-framework/reference/core/beans/java/bean-annotation.html)
- [Spring - @PropertySource](https://docs.spring.io/spring-framework/reference/core/beans/environment.html#beans-using-propertysource)
- [Spring Boot - Externalized Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)
- [Spring - @Value](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/value-annotations.html)
- [Spring - @Profile](https://docs.spring.io/spring-framework/reference/core/beans/environment.html#beans-definition-profiles-java)
- [Spring Boot - Profile Specific Files](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.files.profile-specific)
