package net.javaguides.springboot.controller;


import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/becquerel/products/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @PostAuthorize("hasAnyAuthority('manager','admin')")
    public List<Product> getAllEmployees(){

        return productRepository.findAll();
    }

    //posy restapi

    @PostMapping("save")
    public Product createEmployee(@RequestBody Product product){
        // TODO vALIDATION EMAIL
        return productRepository.save(product);

    }
    //get eployee by ID
    @GetMapping("get/{id}")
    @PostAuthorize("hasAnyAuthority('manager')")
    public ResponseEntity<Product> getEmployeeById(@PathVariable long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return  ResponseEntity.ok(product);
    }

    //update rest api
    @PutMapping("update/{id}")
    @PostAuthorize("hasAnyAuthority('manager','admin','user')")
    public  ResponseEntity<Product> updateEmployee(@PathVariable long id, @RequestBody Product productDetails){
        Product updateProduct =productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id:" +id));
        updateProduct.setFirstName(productDetails.getFirstName());
        updateProduct.setLastName(productDetails.getLastName());
        updateProduct.setEmailId(productDetails.getEmailId());

        productRepository.save(productDetails);
        return  ResponseEntity.ok(updateProduct);
    }

    //delete rest apr
    @DeleteMapping("delete/{id}")
    @PostAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<HttpStatus> deleteEmployee(long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee doesnot exist"));
        productRepository.delete(product);

        return  new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }





}
