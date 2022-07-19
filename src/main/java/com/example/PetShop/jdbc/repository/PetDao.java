package com.example.PetShop.jdbc.repository;

import com.example.PetShop.jdbc.model.*;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface PetDao {

    List<Pet> findAll();

    Pet create(Pet pet, Owner owner) throws ValidationException;

    Pet findById(int id) throws ValidationException;

    String deleteById(int id) throws ValidationException;

    Pet update(Pet_Owner_DTO dto) throws ValidationException;

    List<TopName> topName() throws ValidationException;
}
