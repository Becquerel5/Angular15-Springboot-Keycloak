package net.javaguides.springboot;

import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SpringbootbackendApplication implements CommandLineRunner {

    @Value("${spring.datasource.name},${server.port}")
    String environementport;

	public static void main(String[] args) {SpringApplication.run(SpringbootbackendApplication.class, args);
	}

	@Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        System.err.println(environementport);
        Product product = new Product();
        product.setFirstName("babay1");
        product.setLastName("babyuuuu1");
        product.setEmailId("just1@gmail.com");
        product.setProductname("Product1");
        productRepository.save(product);

        Product product2 = new Product();
        product2.setFirstName("babay22");
        product2.setLastName("bayyyuu2");
        product2.setEmailId("just22@gmail.com");
        product2.setProductname("Product2");
        productRepository.save(product2);
    }


}
