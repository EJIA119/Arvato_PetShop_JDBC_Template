package com.example.PetShop.jdbc.repository;

import com.example.PetShop.jdbc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.java2d.pipe.hw.ExtendedBufferCapabilities;

import javax.xml.bind.ValidationException;
import java.util.List;

@Repository
public class OwnershipDaoImpl implements OwnershipDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Lazy
    @Autowired
    private OwnerDao ownerDao;

    @Lazy
    @Autowired
    private PetDao petDao;

    @Override
    public List<Pet_Owner_DTO> findAll() {
        String selectQuery = "SELECT * FROM ownership INNER JOIN owners ON ownership.owner_id = owners.id INNER JOIN pet ON ownership.pet_id = pet.id;";
        return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<>(Pet_Owner_DTO.class));
    }

    @Override
    public Ownership createRelation(int pet_id, int owner_id) throws Exception{

        petDao.findById(pet_id);
        ownerDao.findById(owner_id);

        try{
            String insertStatement = "INSERT INTO ownership (pet_id, owner_id) VALUES (?, ?);";
            jdbcTemplate.update(insertStatement, pet_id, owner_id);

            return findByPetId(pet_id);
        }catch(Exception e){
            throw new ValidationException("Create Relation: " + e.getMessage());
        }


    }

    @Override
    public Ownership findByPetId(int pet_id) throws Exception {
        try{
            String selectQuery = "SELECT * FROM ownership WHERE pet_id = ?;";
            return jdbcTemplate.queryForObject(selectQuery, BeanPropertyRowMapper.newInstance(Ownership.class), pet_id);
        }catch(Exception e){
            throw new Exception("Find by pet id: " + e.getMessage());
        }

    }

    @Override
    public boolean isExistRecord(int pet_id) throws Exception {
        try{
            String selectQuery = String.format("SELECT COUNT(pet_id) FROM ownership WHERE pet_id = %d;",pet_id);
            Integer result = jdbcTemplate.queryForObject(selectQuery, Integer.class);

            return result > 0;
        }catch(Exception e){
            throw new Exception("Is Exist Record: " + e.getMessage());
        }
    }

    @Override
    public Ownership findByOwnerId(int owner_id) throws Exception{

        try{
            String selectQuery = "SELECT * FROM ownership WHERE owner_id = ?;";
            return jdbcTemplate.queryForObject(selectQuery, BeanPropertyRowMapper.newInstance(Ownership.class), owner_id);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Ownership updateOwner(int pet_id, int owner_id) throws Exception{

        findByPetId(pet_id);
        petDao.findById(pet_id);
        ownerDao.findById(owner_id);

        try{
            String updateStatement = "UPDATE ownership SET owner_id = ? WHERE (pet_id = ?);";
            jdbcTemplate.update(updateStatement, owner_id, pet_id);
            return findByPetId(pet_id);
        }catch(Exception e){
            throw new Exception("Update Relation: " + e.getMessage());
        }

    }

    @Override
    public void delete(int pet_id) throws Exception {
        findByPetId(pet_id);

        try{

            String deleteStatement = String.format("DELETE FROM ownership WHERE pet_id = %d", pet_id);
            jdbcTemplate.update(deleteStatement);
        }catch(Exception e){
            throw new Exception("Delete Ownership: " + e.getMessage());
        }

    }
}
