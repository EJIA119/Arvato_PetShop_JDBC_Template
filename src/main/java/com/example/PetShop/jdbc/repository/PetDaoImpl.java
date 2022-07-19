package com.example.PetShop.jdbc.repository;

import com.example.PetShop.jdbc.model.Owner;
import com.example.PetShop.jdbc.model.Pet;
import com.example.PetShop.jdbc.model.TopName;
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

    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private String today = dateFormat.format(new Date());

    @Override
    public List<Pet> findAll() {
        String selectQuery = "SELECT * FROM pet;";
        return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<Pet>(Pet.class));
    }

    @Override
    public Pet create(Pet newPet) throws ValidationException {

        if (ownerDao.findById(newPet.getOwner_id()) == null)
            throw new ValidationException(String.format("Owner record with ID (%d) not found.", newPet.getOwner_id()));

        newPet.setDate_created(today);
        newPet.setDate_modified(today);
        System.out.println(newPet.getOwner_id());
        try {
            String insertStatement = "INSERT INTO pet (name, breed,date_created, date_modified, owner_id) VALUES (?, ?, ?, ?, ?);";
            jdbcTemplate.update(insertStatement, newPet.getName(), newPet.getBreed(), newPet.getDate_created(), newPet.getDate_modified(), newPet.getOwner_id());
            return findById(getLatestID());
        } catch (Exception e) {
            throw new ValidationException("Pet information cannot be null.");
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

            String deleteStatement = String.format("DELETE FROM pet WHERE id = %d", id);
            jdbcTemplate.update(deleteStatement);
            return String.format("Pet with ID (%d) has been removed. Pet Info: %s", id, deletePet);

        } catch (Exception e) {
            throw new ValidationException(String.format("Pet ID with (%d) is not found.", id));
        }
    }

    @Override
    public Pet update(Pet pet) throws ValidationException {

        findById(pet.getId());

        try {
            String updateStatement = "UPDATE pet SET name = ?, breed = ?, date_modified = ?, owner_id = ? WHERE (id = ?);";
            jdbcTemplate.update(updateStatement, pet.getName(), pet.getBreed(), today, pet.getOwner_id(), pet.getId());

            return findById(pet.getId());
        } catch (Exception e) {
            throw new ValidationException("Pet information cannot be null.");
        }

    }

    @Override
    public List<TopName> topName() throws ValidationException{
        try{
            String selectQuery = "SELECT p.name, COUNT(p.name) AS Counter FROM pet p GROUP BY p.name ORDER BY Counter DESC";
            return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<TopName>(TopName.class));
        }catch(Exception e){
            throw new ValidationException("Something went wrong.");
        }
    }

    private Integer getLatestID() {
        String selectQuery = "SELECT MAX(id) AS 'LatestID' FROM pet;";
        return jdbcTemplate.queryForObject(selectQuery, Integer.class);
    }
}
