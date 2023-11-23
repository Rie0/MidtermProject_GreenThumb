package com.GreenThumb.Mid.service.interfaces;

import com.GreenThumb.Mid.model.Role;

public interface RoleServiceInterface {
    Role saveRole(Role role);

    void addRoleToUser(String email, String roleName);
}
