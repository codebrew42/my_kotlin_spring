# 1. Spring Bean

<br>

Spring provides a powerful mechanism for managing the lifecycle of application objects, known as Spring Beans.
This mechanism allows for the automatic creation, configuration, and management of objects within the Spring container, reducing complexity and improving maintainability.


<br>

## 1.1. Bean Registration

<br>

There are various ways to register beans in Spring, one of which is by using `@Component` annotations.

<br>

```kotlin
@Component
class SpringBean {
    // Bean registration logic
}
```

<br>

### Learning Test
- Test Method: `cholog.BeanTest.registerBean`
- Task
  - Implement `cholog.bean.SpringBean` to pass the test.

<br>

## 1.2 Bean Autowiring

<br>

You can use the `@Autowired` annotation to use a bean that has been registered in the Spring container without needing to create a new instance each time.

<br>

```kotlin
@Autowired
private lateinit var springBean: SpringBean
```

<br>

### Learning Test
- Test Method: `cholog.BeanTest.autowiredBean`
- Task
  - Implement `cholog.bean.AutowiredBean` to pass the test.

<br>

# 2. Dependency Injection

<br>

There are various ways to manage dependencies between Spring beans registered in the Spring container.
One of the methods is to use annotations, specifically by adding the `@Autowired` annotation to constructors, setters, or fields.

<br>

## 2.1. Constructor Injection

<br>

This method injects dependencies between Spring beans registered in the Spring container through constructors.

<br>

```kotlin
@Service
class PersonService {
    var person: Person
    lateinit var address: Address

    constructor(person: Person) {
        this.person = person
    }

    @Autowired
    constructor(person: Person, address: Address): this(person) {
        this.address = address
    }
}
```

<br>

We can omit the @Autowired annotation on a constructor if the target bean defines only one constructor.

```kotlin
@RestController
class PersonController(val personService: PersonService)
```

<br>

### Learning Test
- Test Method: `cholog.DependencyInjectionTest.constructorInjection`
- Task
  - Implement `cholog.di.ConstructorInjection` to pass the test.

<br>

## 2.2. Method Injection

<br>

This method injects dependencies between Spring beans registered in the Spring container through setter methods.

<br>

```kotlin
@Component
class VehicleDao {
  lateinit var vehicleValueFinder: VehicleValueFinder
  lateinit var vehicleReviewDao: VehicleReviewDao

  @Autowired
  fun initialize(vehicleValueFinder: VehicleValueFinder,
                 vehicleReviewDao: VehicleReviewDao) {
    this.vehicleValueFinder = vehicleValueFinder
    this.vehicleReviewDao = vehicleReviewDao
  }
}
```

<br>

### Learning Test
- Test Method: `cholog.DependencyInjectionTest.setterInjection`
- Task
  - Implement `cholog.di.SetterInjection` to pass the test.

<br>

## 2.3. Field Injection

<br>

This method injects dependencies between Spring beans registered in the Spring container through fields.

<br>

```kotlin
@Service
class InventoryService @Autowired constructor(
        val vehicleDao: VehicleDao
) {
  @Autowired
  private lateinit var dealerDao: DealerDao

  // ...
}
```

<br>

### Learning Test
- Test Method: `cholog.DependencyInjectionTest.autowiredInjection`
- Task
  - Implement `cholog.di.FieldInjection` to pass the test.

<br>

# 3. Component Scan

<br>

Component scanning is a mechanism in Spring that automatically detects and registers beans in the Spring container.

<br>

## 3.1. @ComponentScan

<br>

You can specify the package to scan using the `@ComponentScan` annotation.
During the previous learning test, it worked without using `@ComponentScan` because `@SpringBootApplication` includes `@ComponentScan`.

<br>

```java
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
  // ...
}
```

<br>

### Learning Test
- Test Method: `cholog.ComponentScanTest.scanComponent`
- Task
  - Implement `cholog.scan.ContextConfiguration` to pass the test.

<br>

# 6. Reference
- [Spring - @Component](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-stereotype-annotations)
- [Spring - Dependencies](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-dependencies)
- [Spring - Constructor Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-constructor-injection)
- [Spring - Setter Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-setter-injection)
- [Spring - Field Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-autowired-annotation)
