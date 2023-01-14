package net.javaguides.springboot.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PersonServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class PersonServiceImplTest {

    private Person person = new Person();
    @MockBean
    private PersonRepository personRepository;


    private PersonServiceImpl personServiceImpl;

    @Autowired
    public PersonServiceImplTest(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @Value("${server.port}")
    String port;



    @Test
    void BeforeAllSteps(){
        Person person = new Person();
        Person person1 = new Person();
        Person person2 = new Person();
    }


    @Test
    @Order(1)
    void CreatePerson(){
          person = new Person(1l,"DTFB",20);
         when(personRepository.save((Person) any()))
                 .thenReturn(person);

        Person person1 = new Person(1l,"Mark",20);

        assertSame(person, personServiceImpl.createPerson(person1));
        verify(personRepository).save((Person) any());
    }


    @Test
    @Order(2)
    void Read(){
        Person person2 = new Person(123L,"AAA",18);
        assertSame(person,personServiceImpl.createPerson(person2));
        verify(personRepository).save((Person) any());
    }

    @Test
    @Order(9)
    void testString(){
        System.err.println("Hello"+port);
    }


}
