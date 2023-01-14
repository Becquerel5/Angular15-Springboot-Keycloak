package net.javaguides.springboot;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.ArrayList;

@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SpringbootbackendApplication implements CommandLineRunner {

    @Value("${spring.datasource.name},${server.port}")
    String environementport;

	public static void main(String[] args) {SpringApplication.run(SpringbootbackendApplication.class, args);
        /*String value1 = "Becquerel";
        String value2 = "Becquerel";
        //renvois 0 ci ils sont eguax,wi moin va renvoyer inferieur a 0 et ci
        //plus renvoie superieur a 0
        System.err.println(value1.compareTo(value2));
        //renvoi true ci egal et false cinon
        System.err.println(value1.equals(value2));*/
    }

	@Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        System.err.println(environementport);
        Employee employee = new Employee();
        employee.setFirstName("babay1");
        employee.setLastName("babyuuuu1");
        employee.setEmailId("just1@gmail.com");
        employeeRepository.save(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("babay22");
        employee2.setLastName("bayyyuu2");
        employee2.setEmailId("just22@gmail.com");
        employeeRepository.save(employee2);
    }


}
