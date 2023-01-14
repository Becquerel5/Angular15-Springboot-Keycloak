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
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl employeeServiceImpl;


    @Test
    void testConstructor() {
        assertNull((new ProductServiceImpl(mock(ProductRepository.class))).getAllEmployees());
    }


    @Test
    void testCreateEmployee() {
        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        when(productRepository.save((Product) any())).thenReturn(product);

        Product product1 = new Product();
        product1.setEmailId("42");
        product1.setFirstName("Jane");
        product1.setId(123L);
        product1.setLastName("Doe");
        assertSame(product, employeeServiceImpl.createEmployee(product1));
        verify(productRepository).save((Product) any());
    }


    @Test
    void testCreateEmployee2() {
        when(productRepository.save((Product) any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.createEmployee(product));
        verify(productRepository).save((Product) any());
    }


    @Test
    void testGetEmployeeById() {
        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<Product> actualEmployeeById = employeeServiceImpl.getEmployeeById(123L);
        assertTrue(actualEmployeeById.hasBody());
        assertTrue(actualEmployeeById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualEmployeeById.getStatusCode());
        verify(productRepository).findById((Long) any());
    }


    @Test
    void testGetEmployeeById2() {
        when(productRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(123L));
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getEmployeeById(long)}
     */
    @Test
    void testGetEmployeeById3() {
        when(productRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(123L));
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateEmployee(long, Product)}
     */
    @Test
    void testUpdateEmployee() {
        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        Optional<Product> ofResult = Optional.of(product);

        Product product1 = new Product();
        product1.setEmailId("42");
        product1.setFirstName("Jane");
        product1.setId(123L);
        product1.setLastName("Doe");
        when(productRepository.save((Product) any())).thenReturn(product1);
        when(productRepository.findById((Long) any())).thenReturn(ofResult);

        Product product2 = new Product();
        product2.setEmailId("42");
        product2.setFirstName("Jane");
        product2.setId(123L);
        product2.setLastName("Doe");
        ResponseEntity<Product> actualUpdateEmployeeResult = employeeServiceImpl.updateEmployee(123L, product2);
        assertTrue(actualUpdateEmployeeResult.hasBody());
        assertTrue(actualUpdateEmployeeResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateEmployeeResult.getStatusCode());
        Product body = actualUpdateEmployeeResult.getBody();
        assertEquals("42", body.getEmailId());
        assertEquals("Doe", body.getLastName());
        assertEquals("Jane", body.getFirstName());
        verify(productRepository).save((Product) any());
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateEmployee(long, Product)}
     */
    @Test
    void testUpdateEmployee2() {
        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.save((Product) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(productRepository.findById((Long) any())).thenReturn(ofResult);

        Product product1 = new Product();
        product1.setEmailId("42");
        product1.setFirstName("Jane");
        product1.setId(123L);
        product1.setLastName("Doe");
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.updateEmployee(123L, product1));
        verify(productRepository).save((Product) any());
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateEmployee(long, Product)}
     */
    @Test
    void testUpdateEmployee3() {
        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        when(productRepository.save((Product) any())).thenReturn(product);
        when(productRepository.findById((Long) any())).thenReturn(Optional.empty());

        Product product1 = new Product();
        product1.setEmailId("42");
        product1.setFirstName("Jane");
        product1.setId(123L);
        product1.setLastName("Doe");
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.updateEmployee(123L, product1));
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteEmployee(long)}
     */
    @Test
    void testDeleteEmployee() {
        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(productRepository).delete((Product) any());
        when(productRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<HttpStatus> actualDeleteEmployeeResult = employeeServiceImpl.deleteEmployee(123L);
        assertNull(actualDeleteEmployeeResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualDeleteEmployeeResult.getStatusCode());
        assertTrue(actualDeleteEmployeeResult.getHeaders().isEmpty());
        verify(productRepository).findById((Long) any());
        verify(productRepository).delete((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteEmployee(long)}
     */
    @Test
    void testDeleteEmployee2() {
        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        Optional<Product> ofResult = Optional.of(product);
        doThrow(new ResourceNotFoundException("An error occurred")).when(productRepository).delete((Product) any());
        when(productRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(123L));
        verify(productRepository).findById((Long) any());
        verify(productRepository).delete((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteEmployee(long)}
     */
    @Test
    void testDeleteEmployee3() {
        doNothing().when(productRepository).delete((Product) any());
        when(productRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(123L));
        verify(productRepository).findById((Long) any());
    }
}

