package com.herokuapp.backend;

import com.herokuapp.backend.email.Email;
import com.herokuapp.backend.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    @Autowired
    private EmailService emailService;

    @GetMapping()
    public String helloWorld() {
        return "Hello World! This is TaxiHub :)";
    }


    @GetMapping("test")
    @PreAuthorize("hasAnyAuthority('CLIENT','DRIVER','CORPORATION')")
    public String display() {
        return "Hello World test!";
    }

    @GetMapping("email")
    public String emailSent() {
        Email email = new Email();
        email.setTo("filipandersen80@outlook.com");
        email.setSubject("Test Email");
        email.setContent("Test Email - the email service works");
        emailService.send(email);
        return "Email was sent";
    }
}
