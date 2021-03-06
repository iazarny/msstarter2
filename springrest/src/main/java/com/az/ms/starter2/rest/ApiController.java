package com.az.ms.starter2.rest;


import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Api("Api endpoint")
public class ApiController {

    @RequestMapping(name = "/api",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index() {
        return new ResponseEntity<String>("Api module presented", HttpStatus.OK);
    }

}
