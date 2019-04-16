package com.rest.controllers;

import com.rest.domains.Adress;
import com.rest.services.AdressServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController

public class AdressController {

    AdressServiceImpl adressServiceImpl;
    AdressController(AdressServiceImpl adressServiceImpl){
        this.adressServiceImpl=adressServiceImpl;
    }
    @GetMapping("/adresses")
    public void getAllAdress() {
        adressServiceImpl.findAll();

    }

    @PostMapping("/adresses")
    public void saveAdress(@RequestBody Adress adress) {
        adressServiceImpl.save(adress);

    }
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
