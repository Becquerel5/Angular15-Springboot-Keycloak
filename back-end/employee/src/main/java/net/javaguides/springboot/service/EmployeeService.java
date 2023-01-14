package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee createEmployee(Employee employee);



    ResponseEntity<Employee> getEmployeeById(long id);

    ResponseEntity<Employee> updateEmployee(long id, Employee employeeDetails);
    ResponseEntity<HttpStatus> deleteEmployee(long id);

    Employee findByEmail(String employeeemail);

    public void deleteemployee(Long id);
}
