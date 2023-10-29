package com.GreenThumb.Mid.service.impl;

import com.GreenThumb.Mid.model.Volunteer;
import com.GreenThumb.Mid.repository.VolunteerRepository;
import com.GreenThumb.Mid.service.interfaces.IVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService implements IVolunteerService {
    @Autowired
    VolunteerRepository volunteerRepository;

//  ***************************************************  GET  ****************************************************
    @Override //Get Volunteers by ID
    public Volunteer getVolunteerByID(@PathVariable Integer volunteerID){
        Optional<Volunteer> volunteerOptional = volunteerRepository.findById(volunteerID);
        if(volunteerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Volunteer with ID: "+ volunteerID +" not found"); //Check if the volunteer exists, send a message if not.
        return volunteerOptional.get();
    }
    @Override //Get a list of volunteers with names containing a given string. if no string is provided in the post request return volunteers containing "john".
    public List<Volunteer> getAllByVolunteerNameContaining(@RequestParam(defaultValue = "john") String str){
        List<Volunteer> matchingVolunteers = volunteerRepository.findByVolunteerNameContaining(str);
        if(matchingVolunteers.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No volunteers with String: "+ str +"."); //Check if the volunteers exists, send a message if not.
        return matchingVolunteers;
    }//  ***************************************************  PUT  ****************************************************
    @Override //Update a volunteer by ID
    public void updateVolunteer(Volunteer volunteer, Integer volunteerID) {
        Optional<Volunteer> volunteerOptional = volunteerRepository.findById(volunteerID);
        if (volunteerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Volunteer with ID: "+ volunteerID +" not found"); //Check if the volunteer exists, send a message if not.
        //Set the new volunteer info
        volunteer.setVolunteerId(volunteerID);// Set the data in the Volunteer object
        volunteerRepository.save(volunteer);// Save the updated volunteer to the repository
    }
//  ****************************************************  PATCH  ****************************************************
    @Override //Update a volunteer's email by ID
    public void updateVolunteerEmail(String volunteerEmail, Integer volunteerID) {
        Optional<Volunteer> volunteerOptional = volunteerRepository.findById(volunteerID);
        if (volunteerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Volunteer with ID: "+ volunteerID +" not found"); //Check if the volunteer exists, send a message if not.
        Volunteer volunteer = volunteerOptional.get();
        volunteer.getVolunteerInfo().setVolunteerEmail(volunteerEmail);// Set the new email in the Volunteer object
        volunteerRepository.save(volunteer);
    }
//  **************************************************  DELETE  ********************************************************
    @Override //Delete a volunteer by ID
    public void deleteVolunteer(Integer volunteerID) {
        Optional<Volunteer> volunteerOptional = volunteerRepository.findById(volunteerID);
        if (volunteerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Volunteer with ID: "+ volunteerID +" not found"); //Check if the volunteer exists, send a message if not.
        volunteerRepository.deleteById(volunteerID);// delete the Volunteer object
    }


}
