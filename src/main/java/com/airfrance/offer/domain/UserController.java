package com.airfrance.offer.domain;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/test")
    public ResponseEntity<String> getTest(){

        return new ResponseEntity<>("test", HttpStatus.OK);

    }
}
