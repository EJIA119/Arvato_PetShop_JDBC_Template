package com.example.PetShop.jdbc.repository;

import com.example.PetShop.jdbc.model.Ownership;

import java.util.List;

public interface OwnershipDao {

    List<Ownership> findAll();

    Ownership create(Ownership ownership) throws Exception;

    Ownership findByPetId(int pet_id) throws Exception;

    boolean isExistRecord(int pet_id) throws Exception;

    Ownership findByOwnerId(int owner_id) throws Exception;

    Ownership update(Ownership ownership) throws Exception;

    void delete(int pet_id) throws Exception;

}
