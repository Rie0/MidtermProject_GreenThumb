package com.GreenThumb.Mid.controller.impl;

import com.GreenThumb.Mid.model.Volunteer;
import com.GreenThumb.Mid.model.VolunteerInfo;
import com.GreenThumb.Mid.repository.VolunteerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class VolunteerControllerTest {
//    SETUP
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    VolunteerRepository volunteerRepository;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    Volunteer volunteer;

    @BeforeEach
    public void seUP(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        VolunteerInfo info1 = new VolunteerInfo("0123456789","john@example.com");
        volunteer = new Volunteer("Paul Smith",info1);
        volunteerRepository.save(volunteer);
    }
    @AfterEach
    public void breakDown(){
        volunteerRepository.delete(volunteer);
    }

//     TESTS

    @Test//COMPLEX GET TEST
    void getAllVolunteersByNameContaining_invalidStr_notFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/volunteers/str?str=Sam"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test //PUT TEST
    void updateVolunteer_validBody_VolunteerUpdated() throws Exception {
        volunteer.setVolunteerName("Paul Lee");

        String body = objectMapper.writeValueAsString(volunteer);

        mockMvc.perform(put("/api/volunteers/{volunteerId}", volunteer.getVolunteerId())
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(volunteerRepository.findAll().toString().contains("Paul Lee"));
    }

}