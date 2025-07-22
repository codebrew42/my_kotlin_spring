# 1. JdbcTemplate

<br>

Spring provides various tools and abstractions to simplify database access.  
**JDBC (Java Database Connectivity)** is the standard API for interacting with relational databases in Java.  
`JdbcTemplate` is a helper class provided by Spring that makes working with JDBC much easier and safer.  
It simplifies database interactions and reduces boilerplate code.

`JdbcTemplate` is the core of Spring JDBC.  
Even high-level abstractions internally rely on `JdbcTemplate`.

<br>

It handles the basic workflow of JDBC operations (e.g., statement creation and execution).  
The application code is responsible for providing SQL statements and extracting results.

Key features of `JdbcTemplate`:
- Executes SQL queries
- Performs updates including stored procedures
- Iterates over `ResultSet` rows and maps them to objects
- Translates JDBC exceptions into a consistent and meaningful exception hierarchy under `org.springframework.dao`

<br>

# 2. Querying (SELECT)
<br>

JdbcTemplate provides various methods for executing `SELECT` queries, including:

- `queryForObject`
- `query`
- `queryForList`
- `queryForRowSet`
- `queryForMap`

<br>

# 3. Querying for a Single Object

<br>

You can use `queryForObject` to retrieve a single value, such as a count.

<br>

## 3.1 Object with Count

<br>

The first parameter of queryForObject is the SQL query, and the second parameter is the target class type to map the result to.

<br>

```kotlin
val rowCount = jdbcTemplate.queryForObject("select count(*) from customers", Int::class.java)
```

<br>

### Learning Test
- Test Method: `cholog.QueryingDaoTest.count`
- Task
  - Implement `cholog.QueryingDAO.count` to pass the test.

<br>

## 3.2 Object with Parameter

<br>

You can pass parameters to the SQL query using the third parameter of `queryForObject`.

<br>

```kotlin
val lastName = jdbcTemplate.queryForObject("select last_name from customers where id = ?", String::class.java, id);
```
<br>

### Learning Test
- Test Method: `cholog.QueryingDaoTest.getLastName`
- Task
  - Implement `cholog.QueryingDAO.getLastName` to pass the test.

<br>

## 3.3 Object with RowMapper

<br>

You can use `RowMapper` to map the result set to a custom object.

<br> 

```kotlin
val customer = jdbcTemplate.queryForObject(
  "select id, first_name, last_name from customers where id = ?",
  { rs, _ ->
    Customer(
      id = rs.getLong("id"),
      firstName = rs.getString("first_name"),
      lastName = rs.getString("last_name")
    )
  }, id)
```

<br>

### Learning Test
- Test Method: `cholog.QueryingDaoTest.findCustomerById`
- Task
  - Implement `cholog.QueryingDAO.findCustomerById` to pass the test.

<br>

# 4. Querying for a List

## 4.1 List with RowMapper

<br>

To retrieve multiple records, use the `query` method with a `RowMapper`.

<br>

```kotlin
val customers = jdbcTemplate.query(
    "select id, first_name, last_name from customers",
    { rs, _ ->
        Customer(
            id = rs.getLong("id"),
            firstName = rs.getString("first_name"),
            lastName = rs.getString("last_name")
        )
    }
)
```

<br>

You can also reuse a separately defined `RowMapper`:

<br>

```kotlin
private val customerRowMapper = RowMapper<Customer> { rs: ResultSet, _ ->
  Customer(
    rs.getLong("id"),
    rs.getString("first_name"),
    rs.getString("last_name")
  )
}

// ...

val customers = jdbcTemplate.query("select id, first_name, last_name from customers where first_name = ?", rowMapper, firstName)
```

<br>

### Learning Test
- Test Method: `cholog.QueryingDaoTest.findAllCustomers`
- Task
  - Implement `cholog.QueryingDAO.findAllCustomers` to pass the test.

<br>

# 5. Updating (INSERT, UPDATE, and DELETE)

<br>

You can perform data manipulation using methods such as:

- `update`
- `batchUpdate`
- `execute`

<br>

## 5.1 Update (INSERT)

<br>

You can execute INSERT, UPDATE, and DELETE queries using the update method of JdbcTemplate.

<br>

```kotlin
jdbcTemplate.update("insert into customers (first_name, last_name) values (?, ?)", customer.firstName, customer.lastName)
```

<br>

### Learning Test
- Test Method: `cholog.UpdatingDaoTest.insert`
- Task
  - Implement `cholog.UpdatingDAO.insert` to pass the test.

<br>

## 5.2 Update (DELETE)

<br>

```kotlin
jdbcTemplate.update("delete from customers where id = ?", id.toLong())
```

<br>

### Learning Test
- Test Method: `cholog.UpdatingDaoTest.delete`
- Task
  - Implement `cholog.UpdatingDAO.delete` to pass the test.

<br>

## 5.3 KeyHolder

<br>

You can use JdbcTemplate to insert new data into the database,
and use a KeyHolder to retrieve the generated primary key (e.g., the ID).

<br>

```kotlin
val keyHolder: KeyHolder = GeneratedKeyHolder()
jdbcTemplate.update({
  it.prepareStatement(sql, arrayOf("id")).apply {
    setString(1, customer.firstName)
    setString(2, customer.lastName)
  }
}, keyHolder)

val id = keyHolder.key!!.toLong()
```

<br>

### Learning Test
- Test Method: `cholog.UpdatingDaoTest.keyHolder`
- Task
  - Implement `cholog.UpdatingDAO.insertWithKeyHolder` to pass the test.

<br>

# 6. Reference
- [Spring - JdbcTemplate > Querying](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#jdbc-JdbcTemplate-examples-query)
- [Spring - JdbcTemplate > Updating](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#jdbc-JdbcTemplate-examples-update)
