package com.alexc.demobs.dao;

import com.alexc.demobs.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);

}
