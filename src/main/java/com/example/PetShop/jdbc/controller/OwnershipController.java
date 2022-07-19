package com.example.PetShop.jdbc.controller;


import com.example.PetShop.jdbc.model.Ownership;
import com.example.PetShop.jdbc.repository.OwnershipDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/ownership")
public class OwnershipController {

    @Autowired
    OwnershipDao ownershipDao;

    @GetMapping("/findAll")
    public List<Ownership> findAll() {
        return ownershipDao.findAll();
    }

    @PostMapping("/add")
    public Ownership create(@RequestBody Ownership ownership) throws Exception {
        return ownershipDao.create(ownership);
    }

    @PutMapping("/update")
    public Ownership update(@RequestBody Ownership ownership) throws Exception {
        return ownershipDao.update(ownership);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) throws Exception {
        ownershipDao.delete(id);
    }
}
