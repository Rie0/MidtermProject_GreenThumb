package com.GreenThumb.Mid.service.interfaces;

import com.GreenThumb.Mid.model.Volunteer;

import java.util.List;

public interface IVolunteerService {
    public Volunteer getVolunteerByID(Integer volunteerID);
    public List<Volunteer> getAllByVolunteerNameContaining(String str);
    void updateVolunteer(Volunteer volunteer, Integer volunteerID);
    void updateVolunteerEmail(String volunteerEmail, Integer volunteerID);
    void deleteVolunteer(Integer volunteerID);
}
