package com.example.PetShop.jdbc.controller;

import com.example.PetShop.jdbc.model.ErrorMessage;
import com.example.PetShop.jdbc.model.Owner;
import com.example.PetShop.jdbc.model.Pet;
import com.example.PetShop.jdbc.model.TopName;
import com.example.PetShop.jdbc.repository.OwnerDao;
import com.example.PetShop.jdbc.repository.PetDao;
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
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetDao petDao;

    @GetMapping("/findAll")
    public List<Pet> findAll() {
        return petDao.findAll();
    }

    @GetMapping("/findById/{id}")
    public Pet findById(@PathVariable int id) throws ValidationException {
        return petDao.findById(id);
    }

    @PostMapping("/add")
    public Pet create(@RequestBody Pet newPet) throws ValidationException{
        return petDao.create(newPet);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws ValidationException {
        return petDao.deleteById(id);
    }

    @PutMapping("/update")
    public Pet update(@RequestBody Pet updatePet) throws ValidationException {
        return petDao.update(updatePet);
    }

    @GetMapping("/topName")
    public List<TopName> topName() throws ValidationException {
        return petDao.topName();
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
