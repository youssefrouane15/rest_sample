package com.rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class helloWorldEndPoint {

    private static final String SUCCESS = "success";
    private static final String MESSAGE="message";
    @RequestMapping(value = "/hello", method = GET)
    public Map<String, String> helloWordExample() {
        Map<String, String> response = new HashMap<>();
        response.put(SUCCESS, "true");
        response.put(MESSAGE, "Hello World");
        return response;
    }

}
