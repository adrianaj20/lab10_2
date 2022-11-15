package com.tecsup.petclinic.services;

import java.util.List;


import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

public interface OwnerService {

    /**
     * 
     * @return
     */
    Iterable<Owner> findAll();

    /**
     * 
     * @param ownerDTO
     * @return
     */
    Owner create(Owner owner);

    /**
     * 
     * @param pet
     * @return
     */
    Owner update(Owner owner);

    /**
     * 
     * @param id
     */
    void delete(Long id);

    /**
     * 
     * @param id
     * @return
     */
    Owner findById(long id) throws OwnerNotFoundException;

    /**
     * 
     * @param firstname
     * @return
     */
    List<Owner> findByFirstname(String firstname);

}
