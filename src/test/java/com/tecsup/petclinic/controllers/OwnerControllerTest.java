package com.tecsup.petclinic.controllers;

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.dto.OwnerDTO;


@AutoConfigureMockMvc
@SpringBootTest
public class OwnerControllerTest {
    private static final Logger logger 
			= LoggerFactory.getLogger(OwnerControllerTest.class);

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
	private MockMvc mockMvc;

    /**
     * @throws Exception
     */
    @Test
	public void testFindAllOwners() throws Exception {
        int ID_FIRST = 1;

        this.mockMvc.perform(get("/owners"))
            .andExpect(status().isOk()) // HTTP 200
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                            // ACTUAL      EXPECTED 
            //.andExpect(jsonPath("$", hasSize(SIZE)))
            .andExpect(jsonPath("$[0].id", is(ID_FIRST)));
            //.andExpect(jsonPath("$[212].id", is(ID_LAST)));
    }

    /**
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testFindOwnerOK()throws Exception{
        int ID_SEARCH = 1;
		String FIRST_NAME_OWNER = "George";
        String LAST_NAME_OWNER = "Franklin";
		String ADDRESS = "110 W. Liberty St.";
		String CITY = "Madison";
        String TELEPHONE = "6085551023";

        mockMvc.perform(get("/owners/" + ID_SEARCH)) 
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.firstname", is(FIRST_NAME_OWNER)))
            .andExpect(jsonPath("$.lastname", is(LAST_NAME_OWNER)))
            .andExpect(jsonPath("$.address", is(ADDRESS)))
            .andExpect(jsonPath("$.city", is(CITY)))
            .andExpect(jsonPath("$.telephone", is(TELEPHONE)));
    }

    /**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindOwnerKO() throws Exception {
        int ID_SEARCH = 666;
        mockMvc.perform(get("/owners/" + ID_SEARCH)) // Finding object with ID = 666
				.andExpect(status().isNotFound());

    }

    /**
	 * @throws Exception
	 */
	
	@Test
    public void testCreateOwner() throws Exception {
        String FIRST_NAME_OWNER = "Lennard";
        String LAST_NAME_OWNER = "Idone";
		String ADDRESS = "Santa anita";
		String CITY = "Lima";
        String TELEPHONE = "993046524";

        OwnerDTO newOwner = new OwnerDTO(FIRST_NAME_OWNER, LAST_NAME_OWNER, ADDRESS, CITY, TELEPHONE);

        logger.info(newOwner.toString());
        logger.info(om.writeValueAsString(newOwner));

        mockMvc.perform(post("/owners")
	            .content(om.writeValueAsString(newOwner))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.firstname", is(FIRST_NAME_OWNER)))
	            .andExpect(jsonPath("$.lastname", is(LAST_NAME_OWNER)))
	            .andExpect(jsonPath("$.address", is(ADDRESS)))
                .andExpect(jsonPath("$.city", is(CITY)))
                .andExpect(jsonPath("$.telephone", is(TELEPHONE)));

    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteOwner() throws Exception {
        String FIRST_NAME_OWNER = "Lennard";
        String LAST_NAME_OWNER = "Idone";
		String ADDRESS = "Santa anita";
		String CITY = "Lima";
        String TELEPHONE = "993046524";

        OwnerDTO newOwner = new OwnerDTO(FIRST_NAME_OWNER, LAST_NAME_OWNER, ADDRESS, CITY, TELEPHONE);
        ResultActions mvcActions = mockMvc.perform(post("/owners")
	            .content(om.writeValueAsString(newOwner))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated());

                String response = mvcActions.andReturn().getResponse().getContentAsString();

		Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/owners/" + id ))
                 /*.andDo(print())*/
                .andExpect(status().isOk());
    }

}