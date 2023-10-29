package com.GreenThumb.Mid.repository;

import com.GreenThumb.Mid.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VolunteerRepositoryTest {
    @Autowired
    private VolunteerRepository volunteerRepository;

    @BeforeEach
    public void setUp(){ // Create some sample volunteers and save them to the database for testing
        VolunteerInfo info1 = new VolunteerInfo("0123456789","john@example.com");
        VolunteerInfo info2 = new VolunteerInfo("0224343465","alice@example.com");
        Volunteer volunteer1 = new Volunteer("John Doe",info1);
        Volunteer volunteer2 = new Volunteer("Alice Smith",info2);
        volunteerRepository.saveAll(List.of(volunteer1, volunteer2));
    }
    //@AfterEach
    //public void tearDown(){ //Clears new volunteers
        //volunteerRepository.deleteAll();
    //}

    @Test
    public void findAll_volunteers_volunteerList() {
        // Retrieve all volunteers from the repository
        List<Volunteer> volunteerList = volunteerRepository.findAll();
        // Ensure that the list contains two volunteers
        assertEquals(2,volunteerList.size());
    }

    @Test
    public void findByName_volunteerName_volunteerIsPresent(){
        Optional<Volunteer> volunteer = volunteerRepository.findByVolunteerName("Alice Smith");
        assertTrue(volunteer.isPresent());

    }
    @Test
    public void findAllByVolunteerNameContaining_str_volunteerList() {
        //
        List<Volunteer> volunteerList = volunteerRepository.findByVolunteerNameContaining("Doe");
        // Ensure that the list
        assertEquals(1,volunteerList.size());
    }


}