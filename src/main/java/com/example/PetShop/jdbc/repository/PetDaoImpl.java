package com.example.PetShop.jdbc.repository;

import com.example.PetShop.jdbc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.xml.bind.ValidationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class PetDaoImpl implements PetDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OwnerDao ownerDao;

    @Autowired
    private OwnershipDao ownershipDao;

    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private String today = dateFormat.format(new Date());

    @Override
    public List<Pet> findAll() {
        String selectQuery = "SELECT * FROM pet;";
        return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<Pet>(Pet.class));
    }

    @Override
    public Pet create(Pet newPet, Owner owner) throws ValidationException {


        if(newPet ==null || newPet.getName() == null || newPet.getBreed() == null)
            throw new ValidationException("Pet information could not be null");

        newPet.setId(getLatestID());
        newPet.setDate_created(today);
        newPet.setDate_modified(today);

        try {
            String insertStatement = "INSERT INTO pet (id, name, breed, date_created, date_modified) VALUES (?, ?, ?, ?, ?);";
            jdbcTemplate.update(insertStatement, newPet.getId(), newPet.getName(), newPet.getBreed(), newPet.getDate_created(), newPet.getDate_modified());

            if(owner != null)
                ownershipDao.createRelation(newPet.getId(), owner.getId());

            return findById(newPet.getId());
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public Pet findById(int id) throws ValidationException {
        try {
            String selectQuery = String.format("SELECT * FROM pet WHERE id = %d;", id);
            Pet findPet = jdbcTemplate.queryForObject(selectQuery, BeanPropertyRowMapper.newInstance(Pet.class));
            return findPet;
        } catch (Exception e) {
            throw new ValidationException(String.format("Pet ID with (%d) is not found.", id));
        }
    }

    @Override
    public String deleteById(int id) throws ValidationException {
        try {

            Pet deletePet = findById(id);

            if(ownershipDao.isExistRecord(id))
                ownershipDao.delete(id);

            String deleteStatement = String.format("DELETE FROM pet WHERE id = %d", id);
            jdbcTemplate.update(deleteStatement);
            return String.format("Pet with ID (%d) has been removed. Pet Info: %s", id, deletePet);

        } catch (Exception e) {
            throw new ValidationException(String.format("Pet ID with (%d) is not found.", id));
        }
    }

    @Override
    public Pet update(Pet_Owner_DTO dto) throws ValidationException {

        findById(dto.getPet().getId());

        if(dto.getOwner() != null)
            ownerDao.findById(dto.getOwner().getId());

        try {
            String updateStatement = "UPDATE pet SET name = ?, breed = ?, date_modified = ? WHERE (id = ?);";
            jdbcTemplate.update(updateStatement, dto.getPet().getName(), dto.getPet().getBreed(), today, dto.getPet().getId());

            if(dto.getOwner() != null){
                if(ownershipDao.isExistRecord(dto.getPet().getId())){
                    ownershipDao.updateOwner(dto.getPet().getId(), dto.getOwner().getId());
                }else
                    ownershipDao.createRelation(dto.getPet().getId(), dto.getOwner().getId());
            }

            return findById(dto.getPet().getId());
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }

    }

    @Override
    public List<TopName> topName() throws ValidationException{
        try{
            String selectQuery = "SELECT p.name, COUNT(p.name) AS Counter FROM pet p GROUP BY p.name ORDER BY Counter DESC";
            return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<>(TopName.class));
        }catch(Exception e){
            throw new ValidationException("Something went wrong.");
        }
    }

    private Integer getLatestID() {
        String selectQuery = "SELECT MAX(id) AS 'LatestID' FROM pet;";
        Integer latestID = jdbcTemplate.queryForObject(selectQuery, Integer.class);

        return (latestID == null) ? 1 : ++latestID;
    }
}
