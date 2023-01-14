package net.javaguides.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;


    @Test
    void testConstructor() {
        assertNull((new EmployeeServiceImpl(mock(EmployeeRepository.class))).getAllEmployees());
    }


    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(123L);
        employee.setLastName("Doe");
        when(employeeRepository.save((Employee) any())).thenReturn(employee);

        Employee employee1 = new Employee();
        employee1.setEmailId("42");
        employee1.setFirstName("Jane");
        employee1.setId(123L);
        employee1.setLastName("Doe");
        assertSame(employee, employeeServiceImpl.createEmployee(employee1));
        verify(employeeRepository).save((Employee) any());
    }


    @Test
    void testCreateEmployee2() {
        when(employeeRepository.save((Employee) any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        Employee employee = new Employee();
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(123L);
        employee.setLastName("Doe");
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.createEmployee(employee));
        verify(employeeRepository).save((Employee) any());
    }


    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(123L);
        employee.setLastName("Doe");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<Employee> actualEmployeeById = employeeServiceImpl.getEmployeeById(123L);
        assertTrue(actualEmployeeById.hasBody());
        assertTrue(actualEmployeeById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualEmployeeById.getStatusCode());
        verify(employeeRepository).findById((Long) any());
    }


    @Test
    void testGetEmployeeById2() {
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(123L));
        verify(employeeRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getEmployeeById(long)}
     */
    @Test
    void testGetEmployeeById3() {
        when(employeeRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(123L));
        verify(employeeRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#updateEmployee(long, Employee)}
     */
    @Test
    void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(123L);
        employee.setLastName("Doe");
        Optional<Employee> ofResult = Optional.of(employee);

        Employee employee1 = new Employee();
        employee1.setEmailId("42");
        employee1.setFirstName("Jane");
        employee1.setId(123L);
        employee1.setLastName("Doe");
        when(employeeRepository.save((Employee) any())).thenReturn(employee1);
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);

        Employee employee2 = new Employee();
        employee2.setEmailId("42");
        employee2.setFirstName("Jane");
        employee2.setId(123L);
        employee2.setLastName("Doe");
        ResponseEntity<Employee> actualUpdateEmployeeResult = employeeServiceImpl.updateEmployee(123L, employee2);
        assertTrue(actualUpdateEmployeeResult.hasBody());
        assertTrue(actualUpdateEmployeeResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateEmployeeResult.getStatusCode());
        Employee body = actualUpdateEmployeeResult.getBody();
        assertEquals("42", body.getEmailId());
        assertEquals("Doe", body.getLastName());
        assertEquals("Jane", body.getFirstName());
        verify(employeeRepository).save((Employee) any());
        verify(employeeRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#updateEmployee(long, Employee)}
     */
    @Test
    void testUpdateEmployee2() {
        Employee employee = new Employee();
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(123L);
        employee.setLastName("Doe");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.save((Employee) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);

        Employee employee1 = new Employee();
        employee1.setEmailId("42");
        employee1.setFirstName("Jane");
        employee1.setId(123L);
        employee1.setLastName("Doe");
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.updateEmployee(123L, employee1));
        verify(employeeRepository).save((Employee) any());
        verify(employeeRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#updateEmployee(long, Employee)}
     */
    @Test
    void testUpdateEmployee3() {
        Employee employee = new Employee();
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(123L);
        employee.setLastName("Doe");
        when(employeeRepository.save((Employee) any())).thenReturn(employee);
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.empty());

        Employee employee1 = new Employee();
        employee1.setEmailId("42");
        employee1.setFirstName("Jane");
        employee1.setId(123L);
        employee1.setLastName("Doe");
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.updateEmployee(123L, employee1));
        verify(employeeRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#deleteEmployee(long)}
     */
    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(123L);
        employee.setLastName("Doe");
        Optional<Employee> ofResult = Optional.of(employee);
        doNothing().when(employeeRepository).delete((Employee) any());
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<HttpStatus> actualDeleteEmployeeResult = employeeServiceImpl.deleteEmployee(123L);
        assertNull(actualDeleteEmployeeResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualDeleteEmployeeResult.getStatusCode());
        assertTrue(actualDeleteEmployeeResult.getHeaders().isEmpty());
        verify(employeeRepository).findById((Long) any());
        verify(employeeRepository).delete((Employee) any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#deleteEmployee(long)}
     */
    @Test
    void testDeleteEmployee2() {
        Employee employee = new Employee();
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(123L);
        employee.setLastName("Doe");
        Optional<Employee> ofResult = Optional.of(employee);
        doThrow(new ResourceNotFoundException("An error occurred")).when(employeeRepository).delete((Employee) any());
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(123L));
        verify(employeeRepository).findById((Long) any());
        verify(employeeRepository).delete((Employee) any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#deleteEmployee(long)}
     */
    @Test
    void testDeleteEmployee3() {
        doNothing().when(employeeRepository).delete((Employee) any());
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(123L));
        verify(employeeRepository).findById((Long) any());
    }
}

