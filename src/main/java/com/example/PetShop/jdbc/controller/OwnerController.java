package com.example.PetShop.jdbc.controller;

import com.example.PetShop.jdbc.model.ErrorMessage;
import com.example.PetShop.jdbc.model.Pet;
import com.example.PetShop.jdbc.repository.OwnerDao;
import com.example.PetShop.jdbc.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    OwnerDao ownerDao;

    @GetMapping("/findAll")
    public List<Owner> findAll() {
        return ownerDao.findAll();
    }

    @GetMapping("/findById/{id}")
    public Owner findById(@PathVariable int id) throws ValidationException {
        return ownerDao.findById(id);
    }

    @PostMapping("/add")
    public Owner create(@RequestBody Owner newOwner) throws ValidationException{
        return ownerDao.create(newOwner);
    }

    @GetMapping("/findByPetId/{id}")
    public Owner findByPetId(@PathVariable int id) throws ValidationException {
        return ownerDao.findByPetId(id);
    }

    @GetMapping("/findByPetName/{name}")
    public List<Owner> findByPetName(@PathVariable String name) throws ValidationException {
        return ownerDao.findByPetName(name);
    }

    @GetMapping("/getPetsByOwnerId/{id}")
    public List<Pet> getPetsByOwnerId(@PathVariable int id) throws ValidationException {
        return ownerDao.getPetsByOwnerId(id);
    }

    @GetMapping("/findByDateCreated/{dateCreated}")
    public List<Owner> findByDateCreated(@PathVariable String dateCreated) throws ValidationException {
        return ownerDao.findByDateCreated(dateCreated);
    }

    @ControllerAdvice
    public class RestExceptionHandler extends ResponseEntityExceptionHandler {

        @Override
        protected ResponseEntity<Object> handleHttpMessageNotReadable(
                HttpMessageNotReadableException ex, HttpHeaders headers,
                HttpStatus status, WebRequest request) {

            return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.toString(),"Invalid Request"), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorMessage exceptionHandler(ValidationException e){
        return new ErrorMessage("400",e.getMessage());
    }

}
