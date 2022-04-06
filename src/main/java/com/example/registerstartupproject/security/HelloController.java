package com.example.registerstartupproject.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//ControllerForTestingSecurity
public class HelloController {
    @GetMapping("/")
    public String hello(@AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        return "Hello world";
    }

    @GetMapping("/secured")
    public String helloSecured(@CurrentSecurityContext SecurityContext user) {
        System.out.println(user);
        return "Hello" + user.getAuthentication().getName();
    }
}