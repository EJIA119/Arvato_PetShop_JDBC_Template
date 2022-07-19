package com.example.PetShop.jdbc.repository;

import com.example.PetShop.jdbc.model.Ownership;
import com.example.PetShop.jdbc.model.Pet_Owner_DTO;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface OwnershipDao {

    List<Pet_Owner_DTO> findAll();

    Ownership createRelation(int pet_id, int owner_id) throws Exception;

    Ownership findByPetId(int pet_id) throws Exception;

    boolean isExistRecord(int pet_id) throws Exception;

    Ownership findByOwnerId(int owner_id) throws Exception;

    Ownership updateOwner(int pet_id, int owner_id) throws Exception;

    void delete(int pet_id) throws Exception;

}
