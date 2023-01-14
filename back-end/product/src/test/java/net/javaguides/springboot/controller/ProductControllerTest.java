package net.javaguides.springboot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import net.javaguides.springboot.exception.ResourceNotFoundException;

import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductRepository productRepository;


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
        ResponseEntity<HttpStatus> actualDeleteEmployeeResult = productController.deleteEmployee(123L);
        assertNull(actualDeleteEmployeeResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualDeleteEmployeeResult.getStatusCode());
        assertTrue(actualDeleteEmployeeResult.getHeaders().isEmpty());
        verify(productRepository).findById((Long) any());
        verify(productRepository).delete((Product) any());
    }


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
        assertThrows(ResourceNotFoundException.class, () -> productController.deleteEmployee(123L));
        verify(productRepository).findById((Long) any());
        verify(productRepository).delete((Product) any());
    }


    @Test
    void testDeleteEmployee3() {
        doNothing().when(productRepository).delete((Product) any());
        when(productRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productController.deleteEmployee(123L));
        verify(productRepository).findById((Long) any());
    }


    @Test
    void testCreateEmployee() throws Exception {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        String content = (new ObjectMapper()).writeValueAsString(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/becquerel/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testCreateEmployee2() throws Exception {
        when(productRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));

        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        String content = (new ObjectMapper()).writeValueAsString(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/becquerel/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void testGetAllEmployees() throws Exception {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/becquerel/employees");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetAllEmployees2() throws Exception {
        when(productRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/becquerel/employees");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void testGetAllEmployees3() throws Exception {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/becquerel/employees");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetEmployeeById() throws Exception {
        Product product = new Product();
        product.setEmailId("42");
        product.setFirstName("Jane");
        product.setId(123L);
        product.setLastName("Doe");
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/becquerel/employees/{id}", 123L);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"emailId\":\"42\",\"id\":123}"));
    }


    @Test
    void testGetEmployeeById2() throws Exception {
        when(productRepository.findById((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/becquerel/employees/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void testGetEmployeeById3() throws Exception {
        when(productRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/becquerel/employees/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testUpdateEmployee() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(product2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/becquerel/employees/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"emailId\":\"42\",\"id\":123}"));
    }


    @Test
    void testUpdateEmployee2() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(product1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/becquerel/employees/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void testUpdateEmployee3() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(product1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/becquerel/employees/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

