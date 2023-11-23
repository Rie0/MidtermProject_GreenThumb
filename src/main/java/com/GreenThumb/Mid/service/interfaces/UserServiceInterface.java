package com.GreenThumb.Mid.service.interfaces;

import com.GreenThumb.Mid.model.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User userSignupDTO);

    List<User> getUsers();
}
