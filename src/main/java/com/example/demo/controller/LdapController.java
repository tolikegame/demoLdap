package com.example.demo.controller;

import com.example.demo.service.impl.LdapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LdapController {
    @Autowired
    LdapServiceImpl ldapService;

    @PostMapping("/saveLdap")
    public boolean saveLdap(){
        return ldapService.saveLdap();
    }
}
