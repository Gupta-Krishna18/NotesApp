<<<<<<< HEAD
# Employee API — Day 7 + Day 8 Practice

A layered Spring Boot REST API using Java 17+, Spring Web, Spring Data JPA, Bean Validation, and MySQL.

## Architecture

```text
src/main/java/com/krishna/employeeapi/
├── EmployeeApiApplication.java
├── controller/
│   └── EmployeeController.java
├── service/
│   └── EmployeeService.java
├── repository/
│   └── EmployeeRepository.java
├── entity/
│   └── Employee.java
├── dto/
│   ├── EmployeeRequest.java
│   └── EmployeePatchRequest.java
└── exception/
    ├── ApiError.java
    ├── DuplicateEmailException.java
    ├── ResourceNotFoundException.java
    └── GlobalExceptionHandler.java
```

Request flow: Client/Postman -> DispatcherServlet -> Controller -> Service -> Repository -> MySQL. The response returns to the client as JSON.

## Requirements

- JDK 17 or newer
- Maven 3.6.3+ (or use IntelliJ's Maven integration)
- MySQL Server
- Postman (optional)

## Configure MySQL

The default configuration uses:
- Database: `employee_api_db`
- Username: `root`
- Password: `change_me`

Change `spring.datasource.username` and `spring.datasource.password` in `src/main/resources/application.properties`, or set environment variables `DB_USERNAME`, `DB_PASSWORD`, and optionally `DB_URL`.

The JDBC URL includes `createDatabaseIfNotExist=true`; the MySQL user needs permission to create the database. Alternatively, create it yourself:

```sql
CREATE DATABASE employee_api_db;
```

For local practice only, `spring.jpa.hibernate.ddl-auto=update` lets Hibernate create/update the table from the entity. For production, use controlled schema migrations instead. Do not commit real passwords.

## Run

From the project directory:

```bash
mvn clean test
mvn spring-boot:run
```

Base URL: `http://localhost:8080`

If using IntelliJ, open the folder containing `pom.xml`, allow Maven to import dependencies, configure the JDK, update the database credentials, and run `EmployeeApiApplication`.

## API endpoints

| Method | URL | Purpose | Success |
|---|---|---|---|
| GET | `/api/employees` | List employees | 200 |
| GET | `/api/employees?department=IT` | Filter by department | 200 |
| GET | `/api/employees/1` | Find by ID | 200 |
| POST | `/api/employees` | Create employee | 201 |
| PUT | `/api/employees/1` | Replace all editable fields | 200 |
| PATCH | `/api/employees/1` | Update supplied fields only | 200 |
| DELETE | `/api/employees/1` | Delete employee | 204 |

## Postman examples

### Create employee
- Method: `POST`
- URL: `http://localhost:8080/api/employees`
- Header: `Content-Type: application/json`
- Body -> raw -> JSON:

```json
{
  "name": "Krishna Gupta",
  "email": "krishna@example.com",
  "department": "IT",
  "salary": 25000
}
```

### Get all employees
`GET http://localhost:8080/api/employees`

### Get employee by ID
`GET http://localhost:8080/api/employees/1`

### Search department
`GET http://localhost:8080/api/employees?department=IT`

### Replace employee (PUT)
`PUT http://localhost:8080/api/employees/1`

```json
{
  "name": "Krishna Gupta",
  "email": "krishna@example.com",
  "department": "Engineering",
  "salary": 30000
}
```

### Partially update (PATCH)
`PATCH http://localhost:8080/api/employees/1`

```json
{
  "department": "Backend"
}
```

### Delete employee
`DELETE http://localhost:8080/api/employees/1`

## Concepts to identify in the code

- `@SpringBootApplication`: application entry point and component scanning.
- `@RestController`: REST endpoint controller.
- `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@PatchMapping`, `@DeleteMapping`: route mappings.
- `@PathVariable`: reads the employee ID from the URL.
- `@RequestParam`: reads the optional department query parameter.
- `@RequestBody`: maps JSON request data to a DTO.
- `@Valid`: triggers Bean Validation.
- `@Service`: business logic.
- `JpaRepository`: ready-made database CRUD methods.
- Constructor injection: Spring injects required dependencies.
- `@RestControllerAdvice`: centralized API error handling.
- `ResponseEntity`: controls HTTP status and response body.

## Practice checklist

1. Start the app and create an employee.
2. Retrieve all employees and retrieve one by ID.
3. Filter by department.
4. Try creating another employee with the same email (expect 409).
5. Submit an invalid email or missing field (expect 400).
6. Request an unknown ID (expect 404).
7. Test PUT versus PATCH.
8. Delete an employee (expect 204).
9. Explain the controller -> service -> repository flow without looking at the code.

This is an educational local-development project, not a production-hardened API. Authentication, authorization, pagination, DTO response mapping, database migrations, and more extensive tests are intentionally left as next-step exercises.
=======
# 📝 Notes App – Java File I/O

## 📌 Description
A simple **CLI-based Notes Manager** built using Java **File I/O** concepts.  
It allows users to **add notes** and **view saved notes**, storing them in a text file (`notes.txt`).  

This project demonstrates:
- File writing with **FileWriter**
- File reading with **BufferedReader**
- Exception handling in Java
- Persisting data locall
>>>>>>> d819931e8e4cba26dc5bf831a6bd9b98b80136dc
