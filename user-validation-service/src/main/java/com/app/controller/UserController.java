package com.app.controller;

import com.app.dto.AuthRequest;
import com.app.collection.UserCredential;
import com.app.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private AuthenticationService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return service.saveUser(user);
    }

   /* @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        this.doAuthentication(authRequest.getName(), authRequest.getPassword());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getName());
        } else {
            throw new RuntimeException("invalid access");
        }
    }*/
   @PostMapping("/token")
   public String getToken(@RequestBody  AuthRequest authRequest) {
       Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
       if (authenticate.isAuthenticated())
       return service.generateToken(authRequest.getUsername());
       else
           throw new RuntimeException("Invalid Credential !!!");
   }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
