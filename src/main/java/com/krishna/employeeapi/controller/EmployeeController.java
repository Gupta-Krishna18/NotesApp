package com.krishna.employeeapi.controller;

import com.krishna.employeeapi.dto.EmployeePatchRequest;
import com.krishna.employeeapi.dto.EmployeeRequest;
import com.krishna.employeeapi.entity.Employee;
import com.krishna.employeeapi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(required = false) String department) {
        List<Employee> employees = (department == null || department.isBlank())
                ? employeeService.getAllEmployees()
                : employeeService.getEmployeesByDepartment(department);
        System.out.println("cheking point");
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        System.out.println("*******");
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        Employee created = employeeService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> replaceEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.replaceEmployee(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> patchEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeePatchRequest request) {
        return ResponseEntity.ok(employeeService.patchEmployee(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        System.out.println("For checking the ently point :");
        return ResponseEntity.noContent().build();
    }
}
