package com.example.PetShop.jdbc.repository;

import com.example.PetShop.jdbc.model.Owner;
import com.example.PetShop.jdbc.model.Pet;
import org.apache.el.util.Validation;

import javax.xml.bind.ValidationException;
import java.util.List;


public interface OwnerDao {

    List<Owner> findAll();

    Owner create(Owner owner) throws ValidationException;

    Owner findById(int id) throws ValidationException;

    Owner findByPetId(int id) throws ValidationException;

    List<Owner> findByPetName(String name) throws ValidationException;

    List<Pet> getPetsByOwnerId(int id) throws ValidationException;

    List<Owner> findByDateCreated(String dateCreated) throws ValidationException;
}
