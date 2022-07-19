package com.example.PetShop.jdbc.repository;

import com.example.PetShop.jdbc.model.Owner;
import com.example.PetShop.jdbc.model.Pet;
import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
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
public class OwnerDaoImpl implements OwnerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Lazy
    @Autowired
    private PetDao petDao;

    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private String today = dateFormat.format(new Date());

    public List<Owner> findAll() {
        String selectQuery = "SELECT * FROM owners;";
        return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<Owner>(Owner.class));
    }

    public Owner create(Owner newOwner) throws ValidationException {
        try {

            newOwner.setDate_created(today);
            newOwner.setDate_modified(today);

            String insertStatement = "INSERT INTO owners (`date_created`, `date_modified`, `first_name`, `last_name`) VALUES (?, ?, ?, ?);";
            jdbcTemplate.update(insertStatement, newOwner.getDate_created(), newOwner.getDate_modified(), newOwner.getFirst_name(), newOwner.getLast_name());
            return findById(getLatestID());
        } catch (Exception e) {
            throw new ValidationException("Owner information cannot be null.");
        }
    }

    public Owner findById(int id) throws ValidationException {
        try {
            String selectQuery = String.format("SELECT * FROM owners WHERE id = %d;", id);
            return jdbcTemplate.queryForObject(selectQuery, BeanPropertyRowMapper.newInstance(Owner.class));
        } catch (Exception e) {
            throw new ValidationException(String.format("Owner ID with (%d) is not found.", id));
        }
    }

    @Override
    public Owner findByPetId(int id) throws ValidationException {

        if (petDao.findById(id) == null)
            throw new ValidationException(String.format("Pet with ID (%d) is not found.", id));

        try {
            String selectQuery = "SELECT DISTINCT owners.* FROM owners INNER JOIN pet ON owners.id = pet.owner_id WHERE pet.id = ?;";
            return jdbcTemplate.queryForObject(selectQuery, BeanPropertyRowMapper.newInstance(Owner.class), id);
        } catch (Exception e) {
            throw new ValidationException(String.format("Pet with ID (%d) is not found.", id));
        }
    }

    @Override
    public List<Owner> findByPetName(String name) throws ValidationException {
        try {
            String selectQuery = "SELECT DISTINCT owners.* FROM owners INNER JOIN pet ON owners.id = pet.owner_id WHERE name LIKE CONCAT('%', ? ,'%');";
            return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<Owner>(Owner.class),name);
        } catch (Exception e) {
            throw new ValidationException(String.format("Pet with Name (%s) is not found.", name));
        }
    }

    @Override
    public List<Pet> getPetsByOwnerId(int id) throws ValidationException {
        try{
            String selectQuery = "SELECT pet.* FROM owners INNER JOIN pet ON owners.id = pet.owner_id WHERE owners.id = ?;";
            return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<>(Pet.class), id);
        }catch(Exception e){
            throw new ValidationException(String.format("Owner with ID (%d) is not found.", id));
        }
    }

    @Override
    public List<Owner> findByDateCreated(String dateCreated) throws ValidationException {
        try{
            String selectQuery = "SELECT * FROM owners WHERE date_created = ?;";
            return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<>(Owner.class), dateCreated);
        }catch(Exception e){
            throw new ValidationException(String.format("There is no owner records created on that date : %s", dateCreated));
        }
    }

    private Integer getLatestID() {
        String selectQuery = "SELECT MAX(id) AS 'LatestID' FROM owners;";
        return jdbcTemplate.queryForObject(selectQuery, Integer.class);
    }

}
