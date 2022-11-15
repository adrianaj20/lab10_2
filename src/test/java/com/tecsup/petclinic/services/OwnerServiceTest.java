package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

@SpringBootTest
public class OwnerServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;

	@Test
	public void testCreateOwner(){
		String OWNER_NAME = "Ayoria";
		String OWNER_LASTNAME = "Chagua";
		String ADDRESS = "Mz G Lt 40 Sta Anita";
		String CITY = "Lima";
		String TELEPHONE = "999999999";

		Owner owner = new Owner(OWNER_NAME, OWNER_LASTNAME, ADDRESS, CITY, TELEPHONE);

		Owner ownerCreated = ownerService.create(owner);

		logger.info("OWNER CREATED: " + ownerCreated);
		
		assertThat(ownerCreated.getFirstname(), notNullValue());
		assertThat(ownerCreated.getFirstname(), is(OWNER_NAME));
		assertThat(ownerCreated.getLastname(), is(OWNER_LASTNAME));
		assertThat(ownerCreated.getAddress(), is(ADDRESS));
		assertThat(ownerCreated.getCity(), is(CITY));
		assertThat(ownerCreated.getTelephone(), is(TELEPHONE));
	}

	@Test
	public void testUpdateOwner(){

		String OWNER_NAME = "Ayoria2";
		String OWNER_LASTNAME = "Chagua2";
		String ADDRESS = "Mz G Lt 40 Sta Clara";
		String CITY = "Ica";
		String TELEPHONE = "888888888";
		long create_id = -1;

		String UP_OWNER_NAME = "Ayoria2";
		String UP_OWNER_LASTNAME = "Chagua2";
		String UP_ADDRESS = "Mz G Lt 40 Sta Clara";
		String UP_CITY = "Ica";
		String UP_TELEPHONE = "888888888";

		Owner owner = new Owner(OWNER_NAME, OWNER_LASTNAME, ADDRESS, CITY, TELEPHONE);

		logger.info(">" + owner);
		Owner ownerCreated = ownerService.create(owner);
		logger.info(">>" + ownerCreated);

		create_id = ownerCreated.getId();

		ownerCreated.setFirstname(UP_OWNER_NAME);
		ownerCreated.setLastname(UP_OWNER_LASTNAME);
		ownerCreated.setAddress(UP_ADDRESS);
		ownerCreated.setCity(UP_CITY);
		ownerCreated.setTelephone(UP_TELEPHONE);

		Owner upgradeOwner = ownerService.update(ownerCreated);
		logger.info(">>>>" + upgradeOwner);

		assertThat(create_id, notNullValue());
		assertThat(upgradeOwner.getId(), is(create_id));
		assertThat(upgradeOwner.getFirstname(), is(OWNER_NAME));
		assertThat(upgradeOwner.getLastname(), is(OWNER_LASTNAME));
		assertThat(upgradeOwner.getAddress(), is(ADDRESS));
		assertThat(upgradeOwner.getCity(), is(CITY));
		assertThat(upgradeOwner.getTelephone(), is(TELEPHONE));

	}


	@Test
	public void testFindOwnerById() {

		long ID = 1;
		String firstname = "George";

		Owner owner = null;

		try {
			owner = ownerService.findById(ID);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}

		logger.info("" + owner);
		assertThat(owner.getFirstname(), is(firstname));

	}

	@Test
	public void testFindOwnerByFirstname() {

		String firstname = "George";

		Owner owner = null;

		owner = ownerService.findByFirstname(firstname).get(0);

		logger.info("" + owner);
		assertThat(owner.getFirstname(), is(firstname));
	}

	@Test
	public void testDeletePet() {
		String FIRSTNAME = "Bird";
		String LASTNAME = "Churchill";
		String ADDRESS = "";
		String CITY = "";
        String TELEPHONE = "";

		Owner owner = new Owner(FIRSTNAME, LASTNAME, ADDRESS, CITY ,TELEPHONE);
		owner = ownerService.create(owner);
		logger.info("" + owner);


		ownerService.delete(owner.getId());
		
		try {
			ownerService.findById(owner.getId());
			fail("Owner id = " + owner.getId() + " has not delete");
		} catch (OwnerNotFoundException e) {
		}

	}
}