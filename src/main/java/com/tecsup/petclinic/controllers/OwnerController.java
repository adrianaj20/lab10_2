package com.tecsup.petclinic.controllers;

import java.security.Provider.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.petclinic.dto.PetDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.services.OwnerService;

@RestController
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/owners")
    public Iterable<Owner> getOwners() {
        return ownerService.findAll();
    }

    @PostMapping("/owners")
    @ResponseStatus(HttpStatus.CREATED)
    Owner create(@RequestBody OwnerDTO ownerDTO) {
        return ownerService.create(ownerDTO);
    }

    @GetMapping("/pets/{id}")
    ResponseEntity<Owner> findOne(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(ownerService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/owners/{id}")
    Owner saveOrUpdate(@RequestBody OwnerDTO newOwner, @PathVariable Long id) {
        Owner owner = null;
        try {
            owner = ownerService.findById(id);
            owner = ownerService.update(owner);
        } catch (Exception e) {
            owner = ownerService.create(newOwner);
        }
    }

    @DeleteMapping("/pets/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            ownerService.delete(id);
            return new ResponseEntity<>("" + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}