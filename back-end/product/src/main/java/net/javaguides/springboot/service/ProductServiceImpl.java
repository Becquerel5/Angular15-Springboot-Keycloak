package net.javaguides.springboot.service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllEmployees() {
        return productRepository.findAll();
    }

    @Override
    public Product createEmployee(Product product) {

        product.setLastName(product.getLastName());
        product.setFirstName(product.getFirstName());
        product.setEmailId(product.getEmailId());
        return productRepository.save(product);
    }

    @Override
    public ResponseEntity<Product> getEmployeeById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return  ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> updateEmployee(long id, Product productDetails) {
        Product updateProduct =productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id:" +id));
        updateProduct.setFirstName(productDetails.getFirstName());
        updateProduct.setLastName(productDetails.getLastName());
        updateProduct.setEmailId(productDetails.getEmailId());

        productRepository.save(productDetails);
        return  ResponseEntity.ok(updateProduct);
    }




  /*  public  ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee updateEmployee =productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id:" +id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        productRepository.save(employeeDetails);
        return  ResponseEntity.ok(updateEmployee);
    }

   */

    @Override
    public ResponseEntity<HttpStatus> deleteEmployee(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee doesnot exist"));
        productRepository.delete(product);

        return  new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @Override
    public Product findByEmail(String employeeemail) {
        return productRepository.findByEmailId(employeeemail);
    }

    @Override
    public void deleteemployee(Long id) {
        productRepository.deleteById(id);
    }
}
