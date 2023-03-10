package net.javaguides.springboot.controller;


import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/becquerel/employees/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();
    }

    //posy restapi
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        // TODO vALIDATION EMAIL
        return employeeRepository.save(employee);

    }
    //get eployee by ID
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return  ResponseEntity.ok(employee);
    }

    //update rest api
    @PutMapping("{id}")
    public  ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee updateEmployee =employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id:" +id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(employeeDetails);
        return  ResponseEntity.ok(updateEmployee);
    }

    //delete rest apr
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee doesnot exist"));
        employeeRepository.delete(employee);

        return  new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }





}
