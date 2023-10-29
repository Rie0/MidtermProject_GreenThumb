package com.GreenThumb.Mid.controller.interfaces;

import com.GreenThumb.Mid.controller.dto.VolunteerEmailDTO;
import com.GreenThumb.Mid.model.Volunteer;

import java.util.List;

public interface IVolunteerController {
    public List<Volunteer> getAllVolunteers();
    public Volunteer getVolunteerByID(Integer volunteerID);
    public List<Volunteer> getAllByVolunteerNameContaining(String str);
    public void saveVolunteer(Volunteer volunteer);
    public void updateVolunteer(Volunteer volunteer,Integer volunteerID);
    public void updateVolunteerEmail(VolunteerEmailDTO volunteerEmailDTO, Integer volunteerID);
    public void deleteVolunteer(Integer volunteerID);
}
