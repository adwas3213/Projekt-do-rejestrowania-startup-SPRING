package com.example.registerstartupproject.securityAndUtitilies.Login;

import com.example.registerstartupproject.securityAndUtitilies.Login.LoginCredentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    //It is only for being visable from swagger
    @PostMapping("/login")
    public void login( @RequestBody LoginCredentials credentials)
    {
    }

}
