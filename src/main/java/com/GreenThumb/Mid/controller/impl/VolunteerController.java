package com.GreenThumb.Mid.controller.impl;

import com.GreenThumb.Mid.controller.dto.VolunteerEmailDTO;
import com.GreenThumb.Mid.controller.interfaces.IVolunteerController;
import com.GreenThumb.Mid.model.Volunteer;
import com.GreenThumb.Mid.repository.VolunteerRepository;
import com.GreenThumb.Mid.service.impl.VolunteerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VolunteerController implements IVolunteerController {

    @Autowired
    VolunteerRepository volunteerRepository;
    @Autowired
    VolunteerService volunteerService;
//  ***************************************************  GET  ****************************************************
    @GetMapping("/volunteers")//Get all volunteers
    public List<Volunteer> getAllVolunteers(){
        return volunteerRepository.findAll();
    }
    @GetMapping("/volunteers/{volunteerID}")//Get a volunteer by Id
    public Volunteer getVolunteerByID(@PathVariable Integer volunteerID){
        return volunteerService.getVolunteerByID(volunteerID);
    }
    @GetMapping("/volunteers/str") //Get volunteers containing a specific string, "john" is default
    public List<Volunteer> getAllByVolunteerNameContaining(@RequestParam(defaultValue = "john") String str){
        return volunteerService.getAllByVolunteerNameContaining(str);
    }
//  ****************************************************  POST  ********************************************************
    @PostMapping("/volunteers")//Create a new volunteer
    @ResponseStatus(HttpStatus.CREATED)
    public void saveVolunteer(@RequestBody @Valid Volunteer volunteer){
        volunteerRepository.save(volunteer);
    }
//  ****************************************************  PUT  *********************************************************
    @PutMapping("/volunteers/{volunteerID}")//Update a Volunteer
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVolunteer(@RequestBody @Valid Volunteer volunteer, @PathVariable Integer volunteerID){
        volunteerService.updateVolunteer(volunteer, volunteerID);
    }
//  ****************************************************  PATCH  *******************************************************
    @PatchMapping("/volunteers/email/{volunteerID}")//Update a volunteer's email
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVolunteerEmail(@RequestBody @Valid VolunteerEmailDTO volunteerEmailDTO,@PathVariable Integer volunteerID){
        volunteerService.updateVolunteerEmail(volunteerEmailDTO.getVolunteerEmail(), volunteerID);
    }
//  **************************************************  DELETE  ********************************************************
    @DeleteMapping("/volunteers/{volunteerID}")//Delete a volunteer
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVolunteer(@PathVariable Integer volunteerID){
        volunteerService.deleteVolunteer(volunteerID);
    }
}
