package com.GreenThumb.Mid.repository;

import com.GreenThumb.Mid.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
    Optional<Volunteer> findByVolunteerName(String volunteerName);
    List<Volunteer> findByVolunteerNameContaining(String str);
}
