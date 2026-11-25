package com.krishna.employeeapi.service;

import com.krishna.employeeapi.dto.EmployeePatchRequest;
import com.krishna.employeeapi.dto.EmployeeRequest;
import com.krishna.employeeapi.entity.Employee;
import com.krishna.employeeapi.exception.DuplicateEmailException;
import com.krishna.employeeapi.exception.ResourceNotFoundException;
import com.krishna.employeeapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor injection: Spring provides the repository bean.
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return findEmployee(id);
    }

    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartmentIgnoreCase(department);
    }

    public Employee createEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new DuplicateEmailException("An employee with this email already exists.");
        }

        Employee employee = new Employee(
                request.getName().trim(),
                request.getEmail().trim().toLowerCase(),
                request.getDepartment().trim(),
                request.getSalary()
        );
        return employeeRepository.save(employee);
    }

    public Employee replaceEmployee(Long id, EmployeeRequest request) {
        Employee employee = findEmployee(id);

        if (employeeRepository.existsByEmailIgnoreCaseAndIdNot(request.getEmail(), id)) {
            throw new DuplicateEmailException("Another employee already uses this email.");
        }

        employee.setName(request.getName().trim());
        employee.setEmail(request.getEmail().trim().toLowerCase());
        employee.setDepartment(request.getDepartment().trim());
        employee.setSalary(request.getSalary());

        return employeeRepository.save(employee);
    }

    public Employee patchEmployee(Long id, EmployeePatchRequest request) {
        Employee employee = findEmployee(id);

        if (request.getName() != null) {
            employee.setName(request.getName().trim());
        }
        if (request.getEmail() != null) {
            String email = request.getEmail().trim().toLowerCase();
            if (employeeRepository.existsByEmailIgnoreCaseAndIdNot(email, id)) {
                throw new DuplicateEmailException("Another employee already uses this email.");
            }
            employee.setEmail(email);
        }
        if (request.getDepartment() != null) {
            employee.setDepartment(request.getDepartment().trim());
        }
        if (request.getSalary() != null) {
            employee.setSalary(request.getSalary());
        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = findEmployee(id);
        employeeRepository.delete(employee);
    }

    private Employee findEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));
    }
}
