package com.rest.controllers;

import com.rest.domains.Adress;
import com.rest.domains.Client;
import com.rest.services.AdressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdressController {

    @Autowired
    AdressServiceImpl adressServiceImpl;
    AdressController(AdressServiceImpl adressServiceImpl){
        this.adressServiceImpl=adressServiceImpl;
    }
    @GetMapping("/adress")
    public void getAllAdress() {
        adressServiceImpl.findAll();

    }

    @PostMapping("/adress")
    public void saveAdress(@RequestBody Adress adress) {
        adressServiceImpl.save(adress);

    }

}
