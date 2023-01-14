package net.javaguides.springboot.service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {

        employee.setLastName(employee.getLastName());
        employee.setFirstName(employee.getFirstName());
        employee.setEmailId(employee.getEmailId());
        return employeeRepository.save(employee);
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return  ResponseEntity.ok(employee);
    }

    @Override
    public ResponseEntity<Employee> updateEmployee(long id, Employee employeeDetails) {
        Employee updateEmployee =employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id:" +id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(employeeDetails);
        return  ResponseEntity.ok(updateEmployee);
    }




  /*  public  ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee updateEmployee =employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id:" +id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(employeeDetails);
        return  ResponseEntity.ok(updateEmployee);
    }

   */

    @Override
    public ResponseEntity<HttpStatus> deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee doesnot exist"));
        employeeRepository.delete(employee);

        return  new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @Override
    public Employee findByEmail(String employeeemail) {
        return employeeRepository.findByEmailId(employeeemail);
    }

    @Override
    public void deleteemployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
