package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<Product> getAllEmployees();

    Product createEmployee(Product product);



    ResponseEntity<Product> getEmployeeById(long id);

    ResponseEntity<Product> updateEmployee(long id, Product productDetails);
    ResponseEntity<HttpStatus> deleteEmployee(long id);

    Product findByEmail(String employeeemail);

    public void deleteemployee(Long id);
}
