package net.javaguides.springboot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupplierController {

    @GetMapping("/supplier")
    public String getSupplier(){
        return "supplier";
    }
}
