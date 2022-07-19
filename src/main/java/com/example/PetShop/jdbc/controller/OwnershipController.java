package com.example.PetShop.jdbc.controller;


import com.example.PetShop.jdbc.model.Owner;
import com.example.PetShop.jdbc.model.Ownership;
import com.example.PetShop.jdbc.model.Pet_Owner_DTO;
import com.example.PetShop.jdbc.repository.OwnerDao;
import com.example.PetShop.jdbc.repository.OwnershipDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/ownership")
public class OwnershipController {

    @Autowired
    OwnershipDao ownershipDao;

    @GetMapping("/findAll")
    public List<Pet_Owner_DTO> findAll() {
        return ownershipDao.findAll();
    }
}
