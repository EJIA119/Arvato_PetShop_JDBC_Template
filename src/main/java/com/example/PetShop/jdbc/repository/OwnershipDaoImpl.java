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

    @Override
    public List<Ownership> findAll() {
        String selectQuery = "SELECT * FROM ownership;";
        return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<>(Ownership.class));
    }

    @Override
    public Ownership create(Ownership ownership) throws Exception{

        try{
            String insertStatement = "INSERT INTO ownership (pet_id, owner_id) VALUES (?, ?);";
            jdbcTemplate.update(insertStatement, ownership.getPet_id(), ownership.getOwner_id());

            return findByPetId(ownership.getPet_id());
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
    public Ownership update(Ownership ownership) throws Exception{

        try{
            String updateStatement = "UPDATE ownership SET owner_id = ? WHERE (pet_id = ?);";
            jdbcTemplate.update(updateStatement, ownership.getOwner_id(), ownership.getPet_id());
            return findByPetId(ownership.getPet_id());
        }catch(Exception e){
            throw new Exception("Update Relation: " + e.getMessage());
        }

    }

    @Override
    public void delete(int pet_id) throws Exception {

        try{
            String deleteStatement = String.format("DELETE FROM ownership WHERE pet_id = %d", pet_id);
            jdbcTemplate.update(deleteStatement);
        }catch(Exception e){
            throw new Exception("Delete Ownership: " + e.getMessage());
        }

    }
}
