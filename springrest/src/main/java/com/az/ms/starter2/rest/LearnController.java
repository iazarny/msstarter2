package com.az.ms.starter2.rest;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;


@CrossOrigin
@RestController
@Api("Learn endpoint")
public class LearnController {
    @RequestMapping(value = "/learn.json")
    public @ResponseBody  byte[] index() {
        return "{}".getBytes(StandardCharsets.UTF_8);
    }
}
