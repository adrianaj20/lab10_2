package com.tecsup.petclinic.repositories;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.*;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findByFirstname(String firtname);
}